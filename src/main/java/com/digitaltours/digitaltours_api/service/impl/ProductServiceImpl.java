package com.digitaltours.digitaltours_api.service.impl;

import com.digitaltours.digitaltours_api.dto.ProductDTO;
import com.digitaltours.digitaltours_api.entities.ProductEntity;
import com.digitaltours.digitaltours_api.exceptions.ResourceNotFoundException;
import com.digitaltours.digitaltours_api.mappers.ProductMapper;
import com.digitaltours.digitaltours_api.repository.ProductRepository;
import com.digitaltours.digitaltours_api.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public ProductDTO saveProduct(ProductDTO newProduct) {
        //final Integer idProduct = queryIdProduct();
       // newProduct.setId(idProduct.longValue());
        final ProductEntity product = ProductMapper.mapProductDTO(newProduct);

        return ProductMapper.mapProduct(productRepository.save(product));
    }

    @Override
    public Integer queryIdProduct() {
        return productRepository.findMaxIdProduct();
    }

    @Override
    public ProductDTO getProduct(Long id) {
        return productRepository.findById(id)
                .map(ProductMapper::mapProduct)
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado"));
    }

    @Override
    public List<ProductDTO> getAllProducts() {

        try {
            return productRepository.findAll().stream()
                    .map(ProductMapper::mapProduct)
                    .toList();
        } catch (Exception e) {
            throw new RuntimeException("Error al recuperar los productos: " + e.getMessage(), e);
        }
    }

    /* Otra forma de iterar sobre los registros que regresa la BD.
    @Override
    public List<ProductDTO> getAllProducts() {
        final List<ProductDTO> products = new ArrayList<>();

        try {
            productRepository.findAll().forEach(product -> {products.add(ProductMapper.mapProduct(product));
        });
        return products;
        } catch (Exception e) {
            throw new RuntimeException("Error al recuperar los productos: " + e.getMessage(), e);
        }
    }
    */

}