package com.digitaltours.digitaltours_api.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digitaltours.digitaltours_api.dto.FavoriteDTO;
import com.digitaltours.digitaltours_api.entities.FavoritesEntity;
import com.digitaltours.digitaltours_api.entities.ProductEntity;
import com.digitaltours.digitaltours_api.entities.UserEntity;
import com.digitaltours.digitaltours_api.mappers.FavoritesMapper;
import com.digitaltours.digitaltours_api.repository.FavoritesRepository;
import com.digitaltours.digitaltours_api.repository.ProductRepository;
import com.digitaltours.digitaltours_api.repository.UserRepository;
import com.digitaltours.digitaltours_api.service.FavoritesService;

@Service
public class FavoritesServiceImpl implements FavoritesService {

    @Autowired
    private FavoritesRepository favoritesRepository;
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public FavoriteDTO saveFavorite(FavoriteDTO favoriteDTO) {
        favoritesRepository.findByUserIdAndProductId(favoriteDTO.getUserId(), favoriteDTO.getTourId())
                .ifPresent(favorite -> {
                    throw new IllegalArgumentException("El tour ya está en favoritos");
                });

        FavoritesEntity favoritesEntity = FavoritesMapper.mapFavoriteDTO(favoriteDTO);

        ProductEntity product = productRepository.findById(favoriteDTO.getTourId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid tour ID"));
        favoritesEntity.setProduct(product);

        UserEntity user = userRepository.findById(favoriteDTO.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid user ID"));
        favoritesEntity.setUser(user);

        favoritesEntity.setAddedDate(LocalDate.now());

        return FavoritesMapper.mapFavorite(favoritesRepository.save(favoritesEntity));
    }

    @Override
    public List<FavoriteDTO> getFavoritesByUserId(Long userId) {
        return favoritesRepository.findByUserId(userId)
                .stream()
                .map(FavoritesMapper::mapFavorite)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteFavorite(Long userId, Long productId) {
      
        Optional<FavoritesEntity> favorite = favoritesRepository.findByUserIdAndProductId(userId, productId);
        if (favorite.isPresent()) {
            favoritesRepository.delete(favorite.get());
        } else {
            throw new IllegalArgumentException("El tour no está en favoritos");
        }
    }
}
