package com.digitaltours.digitaltours_api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digitaltours.digitaltours_api.dto.CategoryDTO;
import com.digitaltours.digitaltours_api.entities.CategoryEntity;
import com.digitaltours.digitaltours_api.exceptions.ResourceNotFoundException;
import com.digitaltours.digitaltours_api.mappers.CategoryMapper;
import com.digitaltours.digitaltours_api.repository.CategoryRepository;
import com.digitaltours.digitaltours_api.service.CategoryService;


@Service 
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    private CategoryRepository categoryRepository;



    @Override
    public List<CategoryDTO> getAllCategories() {

        try {
            return categoryRepository.findAll().stream()
                    .map(CategoryMapper::mapCategory)
                    .toList();
        } catch (Exception e) {
            throw new RuntimeException("Error al recuperar las categorias: " + e.getMessage(), e);
        }
    }

    @Override
    public CategoryDTO saveCategory(CategoryDTO newCategory) {
        
        final CategoryEntity category = CategoryMapper.mapCategoryDTO(newCategory);

        return CategoryMapper.mapCategory(categoryRepository.save(category));
    }

    @Override
    public Integer queryIdCategory() {
        return categoryRepository.findMaxIdCategory();
    }

    @Override
    public CategoryDTO getCategory(Long id) {
        return categoryRepository.findById(id)
                .map(CategoryMapper::mapCategory)
                .orElseThrow(() -> new ResourceNotFoundException("Categoria no encontrado"));
    }


    
}
