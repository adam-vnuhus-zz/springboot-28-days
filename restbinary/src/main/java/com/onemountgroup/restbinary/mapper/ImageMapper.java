package com.onemountgroup.restbinary.mapper;

import com.onemountgroup.restbinary.model.Image;
import com.onemountgroup.restbinary.model.ImagePOJO;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/*
Sử dụng MapStruct để chuyển đổi giữa ImagePOJO và Image
*/
@Mapper
public interface ImageMapper {
  ImageMapper INSTANCE = Mappers.getMapper(ImageMapper.class);
  ImagePOJO imageToPOJO(Image image);
  Image pojoToImage(ImagePOJO pojo);
}
