package com.digitaltours.digitaltours_api.service;

import java.util.List;

import com.digitaltours.digitaltours_api.dto.CategoryDTO;

public interface CategoryService {
    
    List<CategoryDTO> getAllCategories();
    CategoryDTO saveCategory(CategoryDTO category);
    CategoryDTO getCategory(Long id);
    Integer queryIdCategory();
}
