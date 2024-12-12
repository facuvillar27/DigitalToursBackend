package com.digitaltours.digitaltours_api.mappers;

import org.modelmapper.ModelMapper;

import com.digitaltours.digitaltours_api.dto.ProductCreateDTO;
import com.digitaltours.digitaltours_api.entities.ProductEntity;

public class ProductCreateMapper {

    private ProductCreateMapper() {
        throw new IllegalStateException("No existe un constructor para la clase");
    }

    private static final ModelMapper MAPPER = new ModelMapper();

    public static ProductCreateDTO mapProduct (ProductEntity product) {
        return MAPPER.map(product, ProductCreateDTO.class);
    }

    public static ProductEntity mapProductDTO(ProductCreateDTO productDTO) {
        return MAPPER.map(productDTO, ProductEntity.class);
    }

}
