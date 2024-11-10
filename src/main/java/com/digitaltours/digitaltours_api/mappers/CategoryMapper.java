package com.digitaltours.digitaltours_api.mappers;

import org.modelmapper.ModelMapper;

import com.digitaltours.digitaltours_api.dto.CategoryDTO;
import com.digitaltours.digitaltours_api.entities.CategoryEntity;

public class CategoryMapper {
    
    private CategoryMapper() {
        throw new IllegalStateException("No existe un constructor para la clase");
    }

    private static final ModelMapper MAPPER = new ModelMapper();

    public static CategoryDTO mapCategory (CategoryEntity category) {
        return MAPPER.map(category, CategoryDTO.class);
    }

    public static CategoryEntity mapCategoryDTO(CategoryDTO categoryDTO) {
        return MAPPER.map(categoryDTO, CategoryEntity.class);
    }
}
