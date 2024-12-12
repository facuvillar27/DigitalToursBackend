package com.digitaltours.digitaltours_api.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import com.digitaltours.digitaltours_api.dto.ProductCreateDTO;
import com.digitaltours.digitaltours_api.dto.ProductDTO;
import com.digitaltours.digitaltours_api.dto.ProductViewDTO;
import com.digitaltours.digitaltours_api.entities.ImageEntity;
import com.digitaltours.digitaltours_api.entities.ProductEntity;
import com.digitaltours.digitaltours_api.exceptions.ResourceNotFoundException;
import com.digitaltours.digitaltours_api.exceptions.CustomServiceException;
import com.digitaltours.digitaltours_api.mappers.ProductMapper;
import com.digitaltours.digitaltours_api.mappers.ProductViewMapper;
import com.digitaltours.digitaltours_api.repository.CategoryRepository;
import com.digitaltours.digitaltours_api.repository.CityRepository;
import com.digitaltours.digitaltours_api.repository.ImageRepository;
import com.digitaltours.digitaltours_api.repository.ProductRepository;
import com.digitaltours.digitaltours_api.repository.ProductViewRepository;
import com.digitaltours.digitaltours_api.service.ProductService;
import com.digitaltours.digitaltours_api.service.S3Service;

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

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private S3Service s3Service;

    @Override
    public ProductDTO saveProduct(ProductDTO newProduct) {
    final ProductEntity product = ProductMapper.mapProductDTO(newProduct);

    return ProductMapper.mapProduct(productRepository.save(product));
    }

    @Override
    public ProductDTO saveProductAlt(ProductCreateDTO newProduct, List<MultipartFile> images) {

        try {
        // Crear entidad del producto
        ProductEntity product = new ProductEntity();
        product.setName(newProduct.getName());
        product.setDescription(newProduct.getDescription());
        product.setPrice(newProduct.getPrice());
        product.setDuration(newProduct.getDuration());
        product.setStartTime(newProduct.getStartTime());
        product.setDepartureTime(newProduct.getDepartureTime());

        // Buscar relaciones obligatorias
        product.setCity(cityRepository.findById(newProduct.getCityId())
                .orElseThrow(() -> new ResourceNotFoundException("Ciudad no encontrada")));
        product.setCategory(categoryRepository.findById(newProduct.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Categoría no encontrada")));

        // Guardar el producto
        ProductEntity savedProduct = productRepository.save(product);

        List<ImageEntity> imageEntities = new ArrayList<>();

        for (int i = 0; i < images.size(); i++) {
            MultipartFile file = images.get(i);

            // Manejar carga de archivos a S3
            String imageUrl;
            try {
                imageUrl = s3Service.uploadFile(file);
            } catch (Exception e) {
                throw new FileUploadException("Error al subir archivo: " + file.getOriginalFilename(), e);
            }

            // Crear y guardar entidad de imagen
            ImageEntity image = new ImageEntity();
            image.setUrlImagen(imageUrl);
            image.setPrincipal(i == 0);  // La primera imagen es principal
            image.setProduct(savedProduct);

            imageEntities.add(imageRepository.save(image));
        }

        savedProduct.setImages(imageEntities);

        return ProductMapper.mapProduct(savedProduct);

    } catch (ResourceNotFoundException e) {
        throw new CustomServiceException("Error en datos relacionados: " + e.getMessage(), e);
    } catch (FileUploadException e) {
        throw new CustomServiceException("Error al subir imágenes: " + e.getMessage(), e);
    } catch (Exception e) {
        throw new CustomServiceException("Error al guardar el producto", e);
    }
    }

    // @Override
    // public ProductDTO saveProductAlt(ProductCreateDTO newProduct, MultipartFile image) {

    //     try {
    //         // Crear entidad del producto
    //         ProductEntity product = new ProductEntity();
    //         product.setName(newProduct.getName());
    //         product.setDescription(newProduct.getDescription());
    //         product.setPrice(newProduct.getPrice());
    //         product.setDuration(newProduct.getDuration());

    //         // Buscar relaciones obligatorias
    //         product.setCity(cityRepository.findById(newProduct.getCityId())
    //                 .orElseThrow(() -> new ResourceNotFoundException("Ciudad no encontrada")));
    //         product.setCategory(categoryRepository.findById(newProduct.getCategoryId())
    //                 .orElseThrow(() -> new ResourceNotFoundException("Categoría no encontrada")));

    //         // Guardar el producto
    //         ProductEntity savedProduct = productRepository.save(product);

    //         // Manejar carga de archivo a S3
    //         String imageUrl;
    //         try {
    //             imageUrl = s3Service.uploadFile(image);
    //         } catch (Exception e) {
    //             throw new FileUploadException("Error al subir archivo: " + image.getOriginalFilename(), e);
    //         }

    //         // Crear y guardar entidad de imagen
    //         ImageEntity imageEntity = new ImageEntity();
    //         imageEntity.setUrlImagen(imageUrl);
    //         imageEntity.setPrincipal(true); // Solo una imagen, es principal por defecto
    //         imageEntity.setProduct(savedProduct);

    //         imageRepository.save(imageEntity);
    //         savedProduct.setImages(Collections.singletonList(imageEntity));

    //         return ProductMapper.mapProduct(savedProduct);

    //     } catch (ResourceNotFoundException e) {
    //         throw new CustomServiceException("Error en datos relacionados: " + e.getMessage(), e);
    //     } catch (FileUploadException e) {
    //         throw new CustomServiceException("Error al subir imagen: " + e.getMessage(), e);
    //     } catch (Exception e) {
    //         throw new CustomServiceException("Error al guardar el producto", e);
    //     }
    // }

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

}