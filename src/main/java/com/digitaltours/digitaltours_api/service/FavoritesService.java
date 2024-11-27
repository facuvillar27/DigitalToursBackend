package com.digitaltours.digitaltours_api.service;

import java.util.List;

import com.digitaltours.digitaltours_api.dto.FavoriteDTO;

public interface FavoritesService {
    FavoriteDTO saveFavorite(FavoriteDTO favorite);
    List<FavoriteDTO> getFavoritesByUserId(Long userId);
    void deleteFavorite(Long userId, Long productId);
}
