# Upload binary file lên REST

## 1. Cấu hình [pom.xml](pom.xml)
Cần có các thư viện:
1. spring-boot-starter-web
2. spring-boot-devtools để khởi động lại nhanh
3. lombook
4. spring-boot-starter-data-jpa
5. mapstruct
6. H2: cơ sở dữ liệu SQL in memory
7. log4j2
8. spring-boot-starter-validation để validate dữ liệu gửi lên

## 2. Cấu hình trong [application.properties](src/main/resources/application.properties)
Cấu hình để SpringBoot hỗ trợ upload file đến 200 Mb.
```
spring.datasource.url=jdbc:h2:mem:test
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=
spring.datasource.password=
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.h2.console.enabled=true
spring.jpa.properties.hibernate.hbm2ddl.import_files=
spring.jpa.show-sql=false
spring.jpa.properties.hibernate.format_sql=false

server.port=
## MULTIPART (MultipartProperties)
# Enable multipart uploads
spring.servlet.multipart.enabled=true
# Threshold after which files are written to disk.
spring.servlet.multipart.file-size-threshold=2KB
# Max file size.
spring.servlet.multipart.max-file-size=200MB
# Max Request Size
spring.servlet.multipart.max-request-size=215MB
# All files uploaded through the REST API will be stored in this directory
file.upload-dir=./restbinary/src/main/resources/static
```
Cấu hình máy chủ đầu tiên! Hãy cấu hình ứng dụng Spring Boot để cho phép tải lên, xác định kích thước tệp tối đa có thể được tải lên. Tôi cũng sẽ định cấu hình thư mục mà tất cả các tệp đã tải lên sẽ được lưu trữ.

## 3. Tự động liên kết các thuộc tính với một lớp POJO
```@ConfigurationProperties``` bằng cách sử dụng mà bạn có thể tự động liên kết các thuộc tính được xác định trong [application.properties](src/main/resources/application.properties) gửi đến một lớp POJO.

Chú thích ```@ConfigurationProperties(prefix = "file")``` thực hiện công việc của nó khi khởi động ứng dụng và liên kết tất cả các thuộc tính với tiền tố ```file``` với các trường tương ứng của lớp POJO.

Để bật tính năng ```ConfigurationProperties```, bạn cần thêm chú thích ```@EnableConfigurationProperties``` vào bất kỳ lớp cấu hình nào.

Mở lớp [RestbinaryApplication.java](src/main/java/com/onemountgroup/restbinary/RestbinaryApplication.java) và thêm chú thích ```@EnableConfigurationProperties``` vào nó.

```java
package com.onemountgroup.restbinary;

import com.onemountgroup.restbinary.model.Image;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({
	Image.class
})
public class RestbinaryApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestbinaryApplication.class, args);
	}

}
```

## 4. Viết API để Tải lên và Tải xuống Tệp
Các REST API để tải lên và tải xuống tệp. Tạo một lớp điều khiển mới có tên là ```ImageController.java``` bên trong gói ```com.onemountgroup.restbinary.controller```.

```java
package com.onemountgroup.restbinary.controller;

import java.util.List;

import com.onemountgroup.restbinary.exception.ImageException;
import com.onemountgroup.restbinary.model.ImagePOJO;
import com.onemountgroup.restbinary.model.UploadImageResponse;
import com.onemountgroup.restbinary.service.impl.ImageServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/photo")
public class ImageController {
    @Autowired
    ImageServiceImpl imageServiceImpl;

    @PostMapping()
    public UploadImageResponse uploadFile(@RequestParam("file") MultipartFile file,
            @RequestParam("description") String description) {
        if (file == null) {
            throw new ImageException("You must select the a file for uploading", HttpStatus.BAD_REQUEST);
        }
        String fileName = imageServiceImpl.storeFile(file, description);
        // String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/static/").path(fileName)
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path(fileName)
                .toUriString();
        return new UploadImageResponse(fileName, fileDownloadUri, file.getSize());
    }

    @GetMapping()
    public ResponseEntity<List<ImagePOJO>> getImages() {
        return ResponseEntity.ok().body(imageServiceImpl.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ImagePOJO> getImageById(@PathVariable("id") long id) {
        return ResponseEntity.ok().body(imageServiceImpl.findById(id));
    }
}
```

Viết service lưu trữ tệp trong hệ thống tệp và truy xuất chúng. Tạo một lớp mới được gọi là ```ImageServiceImpl.java``` ở trong ```com.onemountgroup.restbinary.service```.

```java
package com.onemountgroup.restbinary.service.impl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.onemountgroup.restbinary.exception.ImageException;
import com.onemountgroup.restbinary.mapper.ImageMapper;
import com.onemountgroup.restbinary.model.Image;
import com.onemountgroup.restbinary.model.ImagePOJO;
import com.onemountgroup.restbinary.repository.ImageRepository;
import com.onemountgroup.restbinary.service.ImageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ImageServiceImpl implements ImageService {

    private final Path fileStorageLocation;
    private static final String IMAGE_ID_NOT_EXIST = "Image id %d doest not exist.";

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    public ImageServiceImpl(Image fileStorageProperties) {
        this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir()).toAbsolutePath().normalize();
        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new ImageException("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }

    public String storeFile(MultipartFile file, String description) {
        // Normalize file name
        String originalFileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            // Check if the file's name contains invalid characters
            if (originalFileName.contains("..")) {
                throw new ImageException("Sorry! Filename contains invalid path sequence " + originalFileName);
            }

            // Copy file to the target location (Replacing existing file with the same name)
            Path targetLocation = this.fileStorageLocation.resolve(originalFileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            Image newImage = new Image();
            newImage.setFileName(originalFileName);
            newImage.setDescription(description);
            newImage.setLink(ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path(originalFileName).toUriString());
            imageRepository.save(newImage);

            // log.info("inputStream: " + inputStream);
            log.info("originalName: " + originalFileName);
            // log.info("name: " + name);
            log.info("contentType: " + file.getContentType());
            log.info("size: " + file.getSize());
            log.info("description: " + description);

            return originalFileName;

        } catch (IOException ex) {
            log.error("Error when file.getOriginalFilename", ex.getMessage());
            throw new ImageException("Could not store file " + originalFileName + ". Please try again!", ex);
        }
    }

    public Resource loadFileAsResource(String fileName) throws Exception {
        try {
            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists()) {
                return resource;
            } else {
                throw new FileNotFoundException("File not found " + fileName);
            }
        } catch (MalformedURLException ex) {
            throw new FileNotFoundException("File not found " + fileName);
        }
    }

    @Override
    public List<ImagePOJO> getAll() {
        List<Image> images = imageRepository.findAll();
        return images.stream().map(temp -> {
            ImagePOJO imagePOJO = new ImagePOJO();
            imagePOJO.setFileName(temp.getFileName());
            imagePOJO.setLink(temp.getLink());
            imagePOJO.setDescription(temp.getDescription());

            return imagePOJO;
        }).collect(Collectors.toList());
    }

    @Override
    public ImagePOJO findById(Long id) {
        // TODO Auto-generated method stub
        Optional<Image> optionalImage = imageRepository.findById(id);
        if (optionalImage.isPresent()) {
            return ImageMapper.INSTANCE.imageToPOJO(optionalImage.get());
        } else {
            throw new ImageException("Cannot find an image", new Throwable(String.format(IMAGE_ID_NOT_EXIST, id)),
                    HttpStatus.NOT_FOUND);
        }
    }
}
```