package com.digitaltours.digitaltours_api.service;

import java.util.List;

import com.digitaltours.digitaltours_api.dto.ProductDTO;

public interface ProductService {

    ProductDTO saveProduct(ProductDTO product);
    List<ProductDTO> getAllProducts();
    ProductDTO getProduct(Long id);
    Integer queryIdProduct();
    ProductDTO deleteProduct(Long id);
    ProductDTO updateProduct(Long id, ProductDTO productDTO);
}
