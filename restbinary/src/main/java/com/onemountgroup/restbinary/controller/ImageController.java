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
