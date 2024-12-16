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
import com.digitaltours.digitaltours_api.dto.ReviewDTO;
import com.digitaltours.digitaltours_api.service.ReviewService;
import com.digitaltours.digitaltours_api.utils.Meta;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@SuppressWarnings("rawtypes")
@Validated
@CrossOrigin
@RestController
public class ReviewController {

    @Autowired
    private ReviewService reviewService;
    
    private final Meta meta = new Meta(UUID.randomUUID().toString(), "OK", 200);

    @Operation(summary = "Mostrar todos las puntuaciones existentes", description = "Este endpoint permite mostrar todos las puntuaciones.")
    @GetMapping("/v1/review")
    public ApiResponseDTO getAllReviews() {
        try {
            ApiResponseDTO response = new ApiResponseDTO(meta, reviewService.getAllReviews());
            return response;
        } catch (Exception e) {
            return new ApiResponseDTO(new Meta(UUID.randomUUID().toString(), "Error", HttpStatus.NOT_FOUND.value()), e);
        }
    }

    @Operation(summary = "Guardar una nueva puntuacion", description = "Este endpoint permite guardar una puntuacion.")
    @PostMapping(path ="/v1/review")
    public ApiResponseDTO saveReview( @RequestBody @Valid ReviewDTO review) {
        try {
            ApiResponseDTO response = new ApiResponseDTO(meta, reviewService.saveReview(review));
            return response;
        } catch (Exception e) {
            return new ApiResponseDTO(new Meta(UUID.randomUUID().toString(), "Error", HttpStatus.UNPROCESSABLE_ENTITY.value()), e);
        }
    }

    
    @Operation(summary = "Mostrar una puntuacion usando su id", description = "Este endpoint permite mostrar una puntuacion usando su id.")
    @GetMapping("/v1/review/{id:[\\d]+}")
    public ApiResponseDTO getReview(@PathVariable Long id) {
        try {
            ApiResponseDTO response = new ApiResponseDTO(meta, reviewService.getReview(id));
            return response;
        } catch (Exception e) {
            return new ApiResponseDTO(new Meta(UUID.randomUUID().toString(), "Error", HttpStatus.NOT_FOUND.value()), e);
        }
    }


    @Operation(summary = "Eliminar una Puntuacion usando su id", description = "Este endpoint permite eliminar una Puntuacion usando su id.")
    @DeleteMapping("/v1/review/{id:[\\d]+}")
    public ApiResponseDTO deleteReview(@PathVariable Long id) {
        try {
            ApiResponseDTO response = new ApiResponseDTO(meta, reviewService.deleteReview(id));
            return response;
        }catch (Exception e) {
            return new ApiResponseDTO(new Meta(UUID.randomUUID().toString(), "Error", HttpStatus.NOT_FOUND.value()), e);
        }
    }
    
}
