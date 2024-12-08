package com.digitaltours.digitaltours_api.mappers;

import org.modelmapper.ModelMapper;

import com.digitaltours.digitaltours_api.dto.ProductViewDTO;
import com.digitaltours.digitaltours_api.entities.ProductViewEntity;

public class ProductViewMapper {

    private ProductViewMapper() {
        throw new IllegalStateException("No existe un constructor para la clase");
    }

    private static final ModelMapper MAPPER = new ModelMapper();

    public static ProductViewDTO mapProductView(ProductViewEntity product) {
        return MAPPER.map(product, ProductViewDTO.class);
    }

    public static ProductViewEntity mapProductViewDTO(ProductViewDTO productDTO) {
        return MAPPER.map(productDTO, ProductViewEntity.class);
    }

}
