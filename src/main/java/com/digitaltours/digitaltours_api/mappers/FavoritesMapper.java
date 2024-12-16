package com.digitaltours.digitaltours_api.mappers;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

import com.digitaltours.digitaltours_api.dto.FavoriteDTO;
import com.digitaltours.digitaltours_api.entities.FavoritesEntity;

public class FavoritesMapper {
    
    private FavoritesMapper() {
        throw new IllegalStateException("No existe un constructor para la clase");
    }

    private static final ModelMapper MAPPER = new ModelMapper();

    static {
        MAPPER.addMappings(new PropertyMap<FavoritesEntity,FavoriteDTO>() {
            @Override
            protected void configure() {
                map().setTourId(source.getProduct().getId());
            }
        });
    };
    
    public static FavoriteDTO mapFavorite(FavoritesEntity favorite) {
        return MAPPER.map(favorite, FavoriteDTO.class);
    }

    public static FavoritesEntity mapFavoriteDTO(FavoriteDTO favoriteDTO) {
        return MAPPER.map(favoriteDTO, FavoritesEntity.class);
    }

}
