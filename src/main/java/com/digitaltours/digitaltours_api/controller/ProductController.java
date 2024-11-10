package com.digitaltours.digitaltours_api.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.digitaltours.digitaltours_api.dto.ApiResponseDTO;
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
    //private final ProductMessages productMessages = new ProductMessages();

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

    @Operation(summary = "Mostrar un producto usando su id", description = "Este endpoint permite mostrar un producto usando su id.")
    @GetMapping("/v1/products/{id:[\\d]+}")
    public ApiResponseDTO getProduct(@PathVariable Long id) {
        try {
            ApiResponseDTO response = new ApiResponseDTO(meta, productService.getProduct(id));
            return response;
        } catch (Exception e) {
            return new ApiResponseDTO(new Meta(UUID.randomUUID().toString(), "Error", HttpStatus.NOT_FOUND.value()), ProductMessages.PRODUCT_NOT_FOUND);
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

    // Actualizar un producto existente
    @PutMapping("/v1/products/{id}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable Long id, @RequestBody ProductDTO productDTO) {
        ProductDTO updatedProduct = productService.updateProduct(id, productDTO);
        if (updatedProduct != null) {
            return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
