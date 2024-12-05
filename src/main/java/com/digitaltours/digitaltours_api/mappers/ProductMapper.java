package com.digitaltours.digitaltours_api.mappers;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

import com.digitaltours.digitaltours_api.dto.ProductDTO;
import com.digitaltours.digitaltours_api.entities.ImageEntity;
import com.digitaltours.digitaltours_api.entities.ProductEntity;

public class ProductMapper {

    private ProductMapper() {
        throw new IllegalStateException("No existe un constructor para la clase");
    }

    private static final ModelMapper MAPPER = new ModelMapper();

    static {
        MAPPER.addMappings(new PropertyMap<ProductEntity,ProductDTO>() {
            @Override
            protected void configure() {
                using((Converter<List<ImageEntity>, List<String>>) context -> 
                    context.getSource() != null
                        ? context.getSource().stream()
                            .map(ImageEntity::getUrlImagen)
                            .collect(Collectors.toList())
                        : null)
                .map(source.getImages(), destination.getImageUrls());
            }
        });
    }

    public static ProductDTO mapProduct (ProductEntity product) {
        return MAPPER.map(product, ProductDTO.class);
    }

    public static ProductEntity mapProductDTO(ProductDTO productDTO) {
        return MAPPER.map(productDTO, ProductEntity.class);
    }

    

}
