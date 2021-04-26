package com.onemountgroup.restbinary.service.impl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
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
        String fileName = "";

        try {
            // Check if the file's name contains invalid characters
            if (originalFileName.contains("..")) {
                throw new ImageException("Sorry! Filename contains invalid path sequence " + originalFileName);
            }
            String fileExtension = "";

            try {
                fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
            } catch (Exception e) {
                fileExtension = "";
            }

            fileName = "_" + description + fileExtension;

            // Copy file to the target location (Replacing existing file with the same name)
            Path targetLocation = this.fileStorageLocation.resolve(originalFileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            Image newImage = new Image();
            newImage.setFileName(originalFileName);
            newImage.setDescription(description);
            newImage.setLink(ServletUriComponentsBuilder.fromCurrentContextPath().path("/static/")
                    .path(originalFileName).toUriString());
            imageRepository.save(newImage);

            return originalFileName;

        } catch (IOException ex) {
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
