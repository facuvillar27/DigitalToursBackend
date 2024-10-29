package com.digitaltours.digitaltours_api.mappers;

import com.digitaltours.digitaltours_api.dto.ProductDTO;
import com.digitaltours.digitaltours_api.entities.ProductEntity;

import org.modelmapper.ModelMapper;

public class ProductMapper {

    private ProductMapper() {
        throw new IllegalStateException("No existe un constructor para la clase");
    }

    private static final ModelMapper MAPPER = new ModelMapper();

    public static ProductDTO mapProduct (ProductEntity product) {
        return MAPPER.map(product, ProductDTO.class);
    }

    public static ProductEntity mapProductDTO(ProductDTO productDTO) {
        return MAPPER.map(productDTO, ProductEntity.class);
    }

}
