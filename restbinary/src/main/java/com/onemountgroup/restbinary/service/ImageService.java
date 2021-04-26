package com.onemountgroup.restbinary.service;

import java.util.List;

import com.onemountgroup.restbinary.model.ImagePOJO;

public interface ImageService {
    
    ImagePOJO findById(Long id);

    public List<ImagePOJO> getAll();
}
