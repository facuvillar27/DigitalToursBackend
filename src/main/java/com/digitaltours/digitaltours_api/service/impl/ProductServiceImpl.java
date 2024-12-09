package com.digitaltours.digitaltours_api.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.digitaltours.digitaltours_api.dto.ProductDTO;
import com.digitaltours.digitaltours_api.dto.ProductViewDTO;
import com.digitaltours.digitaltours_api.entities.ProductEntity;
import com.digitaltours.digitaltours_api.mappers.ProductMapper;
import com.digitaltours.digitaltours_api.mappers.ProductViewMapper;
import com.digitaltours.digitaltours_api.repository.CategoryRepository;
import com.digitaltours.digitaltours_api.repository.CityRepository;
import com.digitaltours.digitaltours_api.repository.ProductRepository;
import com.digitaltours.digitaltours_api.repository.ProductViewRepository;
import com.digitaltours.digitaltours_api.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductViewRepository productViewRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CityRepository cityRepository;

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
    public ProductDTO getProduct(final Long id) {
        final Optional<ProductEntity> existProduct = productRepository.findById(id);
        ProductDTO productDTO = null;

        if (existProduct.isPresent()) {
            productDTO = ProductMapper.mapProduct(existProduct.get());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Producto No Encontrado");
        }
        return productDTO;
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
    public List<ProductViewDTO> getAllProductsView() {

        try {
            return productViewRepository.findAllTours().stream()
                    .map(ProductViewMapper::mapProductView)
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
            productEntity.setDuration(productDTO.getDuration());
            cityRepository.findById(productDTO.getCity().getId());

            // productEntity.setImage(productDTO.getImage());

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
    
    @Override
    public ProductDTO deleteProduct(final Long product) {
        ProductDTO productEliminado = null;
        final Optional<ProductEntity> existeProduct = productRepository.findById(product);

        if (existeProduct.isPresent()) {
            productEliminado = ProductMapper.mapProduct(existeProduct.get());
            this.productRepository.delete(existeProduct.get());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Producto No Encontrado");
        }
        return productEliminado;
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