package com.digitaltours.digitaltours_api.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.digitaltours.digitaltours_api.dto.ApiResponseDTO;
import com.digitaltours.digitaltours_api.dto.CategoryDTO;
import com.digitaltours.digitaltours_api.service.CategoryService;
import com.digitaltours.digitaltours_api.utils.CategoryMessages;
import com.digitaltours.digitaltours_api.utils.Meta;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@SuppressWarnings("rawtypes")
@Validated
@CrossOrigin
@RestController
public class CategoryController {

    @Autowired
    private CategoryService categoryService;
    
    private final Meta meta = new Meta(UUID.randomUUID().toString(), "OK", 200);

    @Operation(summary = "Mostrar todos las categorias existentes", description = "Este endpoint permite mostrar todos las categorias.")
    @GetMapping("/v1/categories")
    public ApiResponseDTO getAllCategories() {
        try {
            ApiResponseDTO response = new ApiResponseDTO(meta, categoryService.getAllCategories());
            return response;
        } catch (Exception e) {
            return new ApiResponseDTO(new Meta(UUID.randomUUID().toString(), "Error", HttpStatus.NOT_FOUND.value()), e);
        }
    }

    @Operation(summary = "Mostrar una categoria usando su id", description = "Este endpoint permite mostrar una categoria usando su id.")
    @GetMapping("/v1/categories/{id:[\\d]+}")
    public ApiResponseDTO getCategory(@PathVariable Long id) {
        try {
            ApiResponseDTO response = new ApiResponseDTO(meta, categoryService.getCategory(id));
            return response;
        } catch (Exception e) {
            return new ApiResponseDTO(new Meta(UUID.randomUUID().toString(), "Error", HttpStatus.NOT_FOUND.value()), CategoryMessages.CATEGORY_NOT_FOUND);
        }
    }

    @Operation(summary = "Guardar una nueva categoria", description = "Este endpoint le permite a un administrador guardar una nueva categoria.")
    @PostMapping(path ="/v1/categories")
    public ApiResponseDTO saveCategory( @RequestBody @Valid CategoryDTO category) {
        try {
            ApiResponseDTO response = new ApiResponseDTO(meta, categoryService.saveCategory(category));
            return response;
        } catch (Exception e) {
            return new ApiResponseDTO(new Meta(UUID.randomUUID().toString(), "Error", HttpStatus.UNPROCESSABLE_ENTITY.value()), e);
        }
    }

    @Operation(summary = "Eliminar una Category usando su id", description = "Este endpoint permite eliminar una Category usando su id.")
    @DeleteMapping("/v1/categories/{id:[\\d]+}")
    public ApiResponseDTO deleteCategory(@PathVariable Long id) {
        try {
            ApiResponseDTO response = new ApiResponseDTO(meta, categoryService.deleteCategory(id));
            return response;
        }catch (Exception e) {
            return new ApiResponseDTO(new Meta(UUID.randomUUID().toString(), "Error", HttpStatus.NOT_FOUND.value()), e);
        }
    }
}