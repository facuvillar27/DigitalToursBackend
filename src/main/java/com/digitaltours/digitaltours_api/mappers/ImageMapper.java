package com.digitaltours.digitaltours_api.mappers;

import com.digitaltours.digitaltours_api.dto.ImageDTO;
import com.digitaltours.digitaltours_api.entities.ImageEntity;

import org.modelmapper.ModelMapper;

public class ImageMapper {

    private ImageMapper() {
        throw new IllegalStateException("No existe un constructor para la clase");
    }

    private static final ModelMapper MAPPER = new ModelMapper();

    public static ImageDTO mapImage (ImageEntity image) {
        return MAPPER.map(image, ImageDTO.class);
    }

    public static ImageEntity mapImageDTO(ImageDTO imageDTO) {
        return MAPPER.map(imageDTO, ImageEntity.class);
    }

    // Mapea desde ImageDTO a ImageEntity (sin ID)
    public static ImageEntity mapImageSaveDTO(ImageDTO imageDTO) {
        ImageEntity imageEntity = new ImageEntity();
        imageEntity.setName(imageDTO.getName());
        imageEntity.setImageUrl(imageDTO.getImageUrl());
        return imageEntity;
    }

}
