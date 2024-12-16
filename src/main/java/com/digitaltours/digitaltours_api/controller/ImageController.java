package com.digitaltours.digitaltours_api.controller;

import com.digitaltours.digitaltours_api.dto.ApiResponseDTO;
import com.digitaltours.digitaltours_api.dto.ImageDTO;
import com.digitaltours.digitaltours_api.service.ImageService;
import com.digitaltours.digitaltours_api.utils.Meta;
import com.digitaltours.digitaltours_api.utils.ImageMessages;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Validated
@CrossOrigin
@RestController
public class ImageController {

    @Autowired
    private ImageService imageService;

    private final Meta meta = new Meta(UUID.randomUUID().toString(), "OK", 200);

    @Operation(summary = "Mostrar todas las imágenes existentes", description = "Este endpoint permite mostrar todas las imágenes registradas.")
    @GetMapping("/v1/images")
    public ApiResponseDTO getAllImages() {
        try {
            ApiResponseDTO response = new ApiResponseDTO(meta, imageService.getAllImages());
            return response;
        } catch (Exception e) {
            return new ApiResponseDTO(new Meta(UUID.randomUUID().toString(), "Error", HttpStatus.NOT_FOUND.value()), e);
        }
    }

    @Operation(summary = "Mostrar una imagen usando su id", description = "Este endpoint permite mostrar una imagen utilizando su id.")
    @GetMapping(value = "/v1/images/{id:\\d{1,19}}")
    public ApiResponseDTO getImageById(@Parameter(description = "ID de la imagen") @PathVariable Long id) {
        try {
            ApiResponseDTO response = new ApiResponseDTO(meta, imageService.getImageById(id));
            return response;
        } catch (Exception e) {
            return new ApiResponseDTO(new Meta(UUID.randomUUID().toString(), "Error", HttpStatus.NOT_FOUND.value()), ImageMessages.IMAGE_NOT_FOUND);
        }
    }

    @Operation(summary = "Guardar una nueva imagen", description = "Este endpoint le permite a un administrador guardar una nueva imagen.")
    @PostMapping(path = "/v1/images")
    public ApiResponseDTO saveImage(@RequestBody @Validated ImageDTO imageDTO) {
        try {
            ApiResponseDTO response = new ApiResponseDTO(meta, imageService.createImage(imageDTO));
            return response;
        } catch (Exception e) {
            return new ApiResponseDTO(new Meta(UUID.randomUUID().toString(), "Error", HttpStatus.UNPROCESSABLE_ENTITY.value()), e);
        }
    }

    @Operation(summary = "Eliminar una imagen usando su id", description = "Este endpoint permite eliminar una imagen usando su id.")
    @DeleteMapping("/v1/images/{id:[\\d]+}")
    public ResponseEntity<Void> deleteImage(@PathVariable Long id) {
        try {
            imageService.deleteImage(id);
            return ResponseEntity.noContent().build();  // 204 No Content
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();  // 500 Internal Server Error
        }
    }

    @Operation(summary = "Actualizar una imagen", description = "Este endpoint le permite a un administrador actualizar una imagen existente.")
    @PutMapping("/v1/images/{id:[\\d]+}")
    public ApiResponseDTO updateImage(@PathVariable Long id, @RequestBody @Validated ImageDTO imageDTO) {
        try {
            ApiResponseDTO response = new ApiResponseDTO(meta, imageService.updateImage(id, imageDTO));
            return response;
        } catch (Exception e) {
            return new ApiResponseDTO(new Meta(UUID.randomUUID().toString(), "Error", HttpStatus.UNPROCESSABLE_ENTITY.value()), e);
        }
    }
}