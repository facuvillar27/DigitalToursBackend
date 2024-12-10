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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.digitaltours.digitaltours_api.dto.ApiResponseDTO;
import com.digitaltours.digitaltours_api.dto.ProductCreateDTO;
import com.digitaltours.digitaltours_api.dto.ProductDTO;
import com.digitaltours.digitaltours_api.service.ProductService;
import com.digitaltours.digitaltours_api.utils.Meta;
import com.digitaltours.digitaltours_api.utils.ProductMessages;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@SuppressWarnings("rawtypes")
@Validated
@CrossOrigin
@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    private final Meta meta = new Meta(UUID.randomUUID().toString(), "OK", 200);

    @Operation(summary = "Mostrar todos los productos existentes", description = "Este endpoint permite mostrar todos los productos.")
    @GetMapping("/v1/products")
    public ApiResponseDTO getAllProducts() {
        try {
            ApiResponseDTO response = new ApiResponseDTO(meta, productService.getAllProducts());
            return response;
        } catch (Exception e) {
            return new ApiResponseDTO(new Meta(UUID.randomUUID().toString(), "Error", HttpStatus.NOT_FOUND.value()), e);
        }
    }

    @Operation(summary = "Mostrar todos los productos existentes con menos informacion relacionada", description = "Este endpoint permite mostrar todos los productos con menos informacion relacionada.")
    @GetMapping("/v1/products/alt")
    public ApiResponseDTO getAllProductsView() {
        try {
            ApiResponseDTO response = new ApiResponseDTO(meta, productService.getAllProductsView());
            return response;
        } catch (Exception e) {
            return new ApiResponseDTO(new Meta(UUID.randomUUID().toString(), "Error", HttpStatus.NOT_FOUND.value()), e);
        }
    }

    @Operation(summary = "Mostrar una producto usando su id", description = "Este endpoint permite mostrar una producto usando su id.")
    @GetMapping(value = "/v1/products/{id:\\d{1,19}}")
    public ApiResponseDTO getProduct(@PathVariable Long id) {
        try {
            ApiResponseDTO response = new ApiResponseDTO(meta, productService.getProduct(id));
            return response;
        } catch (Exception e) {
            return new ApiResponseDTO(new Meta(UUID.randomUUID().toString(), "Error", HttpStatus.NOT_FOUND.value()), ProductMessages.PRODUCT_NOT_FOUND);
        }
    }


    // @Operation(summary = "Guardar un nuevo producto", description = "Este endpoint le permite a un administrador guardar un nuevo producto.")
    // @PostMapping(path ="/v1/products")
    // public ApiResponseDTO saveProduct( @RequestBody @Valid ProductDTO product) {
    //     try {
    //         ApiResponseDTO response = new ApiResponseDTO(meta, productService.saveProduct(product));
    //         return response;
    //     } catch (Exception e) {
    //         return new ApiResponseDTO(new Meta(UUID.randomUUID().toString(), "Error", HttpStatus.UNPROCESSABLE_ENTITY.value()), e);
    //     }
    // }

    @Operation(summary = "Guardar un nuevo producto", description = "Este endpoint le permite a un administrador guardar un nuevo producto.")
    @PostMapping(path ="/v1/products")
    public ApiResponseDTO saveProduct( @RequestBody @Valid ProductCreateDTO product) {
        try {
            ApiResponseDTO response = new ApiResponseDTO(meta, productService.saveProduct(product));
            return response;
        } catch (Exception e) {
            return new ApiResponseDTO(new Meta(UUID.randomUUID().toString(), "Error", HttpStatus.UNPROCESSABLE_ENTITY.value()), e);
        }
    }

    @Operation(summary = "Eliminar un producto usando su id", description = "Este endpoint permite eliminar un producto usando su id.")
    @DeleteMapping("/v1/products/{id:[\\d]+}")
    public ApiResponseDTO deleteProduct(@PathVariable Long id) {
        try {
            ApiResponseDTO response = new ApiResponseDTO(meta, productService.deleteProduct(id));
            return response;
        }catch (Exception e) {
            return new ApiResponseDTO(new Meta(UUID.randomUUID().toString(), "Error", HttpStatus.INTERNAL_SERVER_ERROR.value()), e);
        }
    }

    @Operation(summary = "Actualizar un producto", description = "Este endpoint le permite a un administrador Actualizar un producto.")
    @PutMapping("/v1/products/{id:[\\d]+}")
    public ApiResponseDTO updateProduct( @PathVariable Long id, @RequestBody @Valid ProductDTO productDTO) {
        try {
            ApiResponseDTO response = new ApiResponseDTO(meta, productService.updateProduct(id, productDTO));
            return response;
        } catch (Exception e) {
            return new ApiResponseDTO(new Meta(UUID.randomUUID().toString(), "Error", HttpStatus.UNPROCESSABLE_ENTITY.value()), e);
        }
    }

}
