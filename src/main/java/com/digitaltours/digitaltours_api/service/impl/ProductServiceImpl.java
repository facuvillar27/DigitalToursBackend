package com.digitaltours.digitaltours_api.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digitaltours.digitaltours_api.dto.ProductDTO;
import com.digitaltours.digitaltours_api.entities.ProductEntity;
import com.digitaltours.digitaltours_api.exceptions.ResourceNotFoundException;
import com.digitaltours.digitaltours_api.mappers.ProductMapper;
import com.digitaltours.digitaltours_api.repository.CategoryRepository;
import com.digitaltours.digitaltours_api.repository.ProductRepository;
import com.digitaltours.digitaltours_api.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

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

    @Override
    public ProductDTO updateProduct(Long id, ProductDTO productDTO) {
        Optional<ProductEntity> existingProduct = productRepository.findById(id);
        if (existingProduct.isPresent()) {
            ProductEntity productEntity = existingProduct.get();

            // Actualiza los campos del producto
            productEntity.setName(productDTO.getName());
            productEntity.setDescription(productDTO.getDescription());
            productEntity.setPrice(productDTO.getPrice());
            productEntity.setImage(productDTO.getImage());

            if (productDTO.getCategory() != null && productDTO.getCategory().getId() != null) {
                categoryRepository.findById(productDTO.getCategory().getId())
                        .ifPresent(productEntity::setCategory);
            }

            // Guarda el producto actualizado
            productEntity = productRepository.save(productEntity);
            return ProductMapper.mapProduct(productEntity);
        }
        throw new RuntimeException("Producto no encontrado para actualizar");
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