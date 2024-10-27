package com.digitaltours.digitaltours_api.controller;

import com.digitaltours.digitaltours_api.dto.ApiResponseDTO;
import com.digitaltours.digitaltours_api.service.ImageService;
import com.digitaltours.digitaltours_api.utils.Meta;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@SuppressWarnings("rawtypes")
@Validated
@CrossOrigin
@RestController
@RequestMapping("images")
public class ImageController {

    @Autowired
    private ImageService imageService;

    private final Meta meta = new Meta(UUID.randomUUID().toString(), "OK", 200);

    @Operation(summary = "Subir una imagen", description = "Este endpoint permite subir una imagen.")
    @PostMapping(path ="/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ApiResponseDTO uploadImage( @RequestParam("file") MultipartFile file) {
        try {
            ApiResponseDTO response = new ApiResponseDTO(meta, imageService.uploadImage(file));
            return response;
        } catch (Exception e) {
            return new ApiResponseDTO(new Meta(UUID.randomUUID().toString(), "Error", HttpStatus.UNPROCESSABLE_ENTITY.value()), e);
        }
    }

    @GetMapping
    public ApiResponseDTO getAllImages() {
        try {
            ApiResponseDTO response = new ApiResponseDTO(meta, imageService.getAllImages());
            return response;
        } catch (Exception e) {
            return new ApiResponseDTO(new Meta(UUID.randomUUID().toString(), "Error", HttpStatus.NOT_FOUND.value()), e);
        }
    }
}
