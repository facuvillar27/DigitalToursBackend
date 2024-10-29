package com.digitaltours.digitaltours_api.controller;

import com.digitaltours.digitaltours_api.dto.ApiResponseDTO;
import com.digitaltours.digitaltours_api.dto.ProductDTO;
import com.digitaltours.digitaltours_api.service.ProductService;
import com.digitaltours.digitaltours_api.utils.Meta;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@SuppressWarnings("rawtypes")
@Validated
@CrossOrigin
@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    private final Meta meta = new Meta(UUID.randomUUID().toString(), "OK", 200);

    @GetMapping("/v1/products")
    public ApiResponseDTO getAllProducts() {
        try {
            ApiResponseDTO response = new ApiResponseDTO(meta, productService.getAllProducts());
            return response;
        } catch (Exception e) {
            return new ApiResponseDTO(new Meta(UUID.randomUUID().toString(), "Error", HttpStatus.NOT_FOUND.value()), e);
        }
    }

    @GetMapping("/v1/products/{id:[\\d]+}")
    public ApiResponseDTO getProduct(@PathVariable Long id) {
        try {
            ApiResponseDTO response = new ApiResponseDTO(meta, productService.getProduct(id));
            return response;
        } catch (Exception e) {
            return new ApiResponseDTO(new Meta(UUID.randomUUID().toString(), "Error", HttpStatus.NOT_FOUND.value()), e);
        }
    }

    @Operation(summary = "Guardar un nuevo producto", description = "Este endpoint le permite a un administrador guardar un nuevo producto.")
    @PostMapping(path ="/v1/products")
    public ApiResponseDTO saveProduct( @RequestBody @Valid ProductDTO product) {
        try {
            ApiResponseDTO response = new ApiResponseDTO(meta, productService.saveProduct(product));
            return response;
        } catch (Exception e) {
            return new ApiResponseDTO(new Meta(UUID.randomUUID().toString(), "Error", HttpStatus.UNPROCESSABLE_ENTITY.value()), e);
        }
    }

}
