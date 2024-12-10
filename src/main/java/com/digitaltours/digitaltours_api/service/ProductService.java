package com.digitaltours.digitaltours_api.service;

import java.util.List;

import com.digitaltours.digitaltours_api.dto.ProductCreateDTO;
import com.digitaltours.digitaltours_api.dto.ProductDTO;
import com.digitaltours.digitaltours_api.dto.ProductViewDTO;

public interface ProductService {

    //ProductDTO saveProduct(ProductDTO product);
    ProductDTO saveProduct(ProductCreateDTO product);
    List<ProductDTO> getAllProducts();
    List<ProductViewDTO> getAllProductsView();
    ProductDTO getProduct(Long id);
    Integer queryIdProduct();
    ProductDTO deleteProduct(Long id);
    ProductDTO updateProduct(Long id, ProductDTO productDTO);
}
