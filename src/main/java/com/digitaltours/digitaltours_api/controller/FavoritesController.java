package com.digitaltours.digitaltours_api.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.digitaltours.digitaltours_api.dto.ApiResponseDTO;
import com.digitaltours.digitaltours_api.dto.FavoriteDTO;
import com.digitaltours.digitaltours_api.service.FavoritesService;
import com.digitaltours.digitaltours_api.utils.Meta;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@SuppressWarnings("rawtypes")
@Validated
@CrossOrigin
@RestController
public class FavoritesController {

    @Autowired
    private FavoritesService favoritesService;

    private final Meta meta = new Meta(UUID.randomUUID().toString(), "OK", 200);

    @Operation(summary = "Mostrar todos los favoritos de un usuario", description = "Este endpoint permite obtener todos los tours favoritos de un usuario usando su id.")
    @GetMapping("/v1/favorites/{userId}")
    public ApiResponseDTO getFavoritesByUserId(@PathVariable Long userId) {
        try {
            ApiResponseDTO response = new ApiResponseDTO(meta, favoritesService.getFavoritesByUserId(userId));
            return response;
        } catch (Exception e) {
            return new ApiResponseDTO(new Meta(UUID.randomUUID().toString(), "Error", HttpStatus.NOT_FOUND.value()), e);
        }
    }

    @Operation(summary = "Agregar un tour a favoritos", description = "Este endpoint permite agregar un tour a la lista de favoritos de un usuario.")
    @PostMapping(path ="/v1/favorites")
    public ApiResponseDTO saveFavorite(@RequestBody @Valid FavoriteDTO favoriteDTO) {
        try {
            ApiResponseDTO response = new ApiResponseDTO(meta, favoritesService.saveFavorite(favoriteDTO));
            return response;
        } catch (Exception e) {
            return new ApiResponseDTO(new Meta(UUID.randomUUID().toString(), "Error", HttpStatus.NOT_FOUND.value()), e);
        }
    }

    @Operation(summary = "Eliminar un tour de favoritos usando el id del usuario y el id del tour", description = "Este endpoint permite eliminar un tour de la lista de favoritos de un usuario usando el id del usuario y el id del tour.")
    @DeleteMapping("/v1/favorites/{userId:[\\d]+}/{productId:[\\d]+}")
    public ApiResponseDTO deleteFavorite(@PathVariable Long userId, @PathVariable Long productId) {
        try {
            favoritesService.deleteFavorite(userId, productId);
            return new ApiResponseDTO(meta, "Favorite deleted successfully");
        } catch (Exception e) {
            return new ApiResponseDTO(new Meta(UUID.randomUUID().toString(), "Error", HttpStatus.NOT_FOUND.value()), "Favorite not found for userId: " + userId + " and productId: " + productId);
        }
    }

}
