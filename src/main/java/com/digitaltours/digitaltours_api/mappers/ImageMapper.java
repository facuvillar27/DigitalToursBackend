package com.digitaltours.digitaltours_api.mappers;

import org.modelmapper.ModelMapper;

import com.digitaltours.digitaltours_api.dto.ImageDTO;
import com.digitaltours.digitaltours_api.entities.ImageEntity;

public class ImageMapper {

    private ImageMapper() {
        throw new IllegalStateException("No existe un constructor para la clase");
    }

    private static final ModelMapper MAPPER = new ModelMapper();

    public static ImageDTO mapImage(ImageEntity imageEntity) {
        return MAPPER.map(imageEntity, ImageDTO.class);
    }

    public static ImageEntity mapImageDTO(ImageDTO imageDTO) {
        return MAPPER.map(imageDTO, ImageEntity.class);
    }
}