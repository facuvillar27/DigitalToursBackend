package com.digitaltours.digitaltours_api.mappers;

import org.modelmapper.ModelMapper;

import com.digitaltours.digitaltours_api.dto.FeatureDTO;
import com.digitaltours.digitaltours_api.entities.FeatureEntity;

public class FeatureMapper {

    private FeatureMapper() {
        throw new IllegalStateException("No existe un constructor para la clase");
    }

    private static final ModelMapper MAPPER = new ModelMapper();

    public static FeatureDTO mapFeature(FeatureEntity featureEntity) {
        return MAPPER.map(featureEntity, FeatureDTO.class);
    }

    public static FeatureEntity mapFeatureDTO(FeatureDTO featureDTO) {
        return MAPPER.map(featureDTO, FeatureEntity.class);
    }
    
}
