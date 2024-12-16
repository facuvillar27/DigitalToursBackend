package com.digitaltours.digitaltours_api.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.digitaltours.digitaltours_api.dto.ApiResponseDTO;
import com.digitaltours.digitaltours_api.service.ProductFeatureService;
import com.digitaltours.digitaltours_api.utils.Meta;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;

@RestController
public class ProductFeatureController {

    @Autowired
    private ProductFeatureService productFeatureService;

    private final Meta meta = new Meta(UUID.randomUUID().toString(), "OK", 200);


    @Operation(summary = "Agregar una característica a un tour", description = "Este endpoint permite agregar una característica a un tour.")
    @PostMapping(value = "/v1/tours/{idTour:\\d{1,19}}/features/{idFeature:\\d{1,19}}")
    public ApiResponseDTO addFeatureToProduct(@Parameter(description = "ID del tour") @PathVariable Long idTour,
            @Parameter(description = "ID de la característica") @PathVariable Long idFeature) {
        try {
            if (productFeatureService.addFeatureToProduct(idTour, idFeature)) {
                return new ApiResponseDTO(meta, "Característica agregada correctamente");
            } else {
                return new ApiResponseDTO(new Meta(UUID.randomUUID().toString(), "Error", HttpStatus.UNPROCESSABLE_ENTITY.value()), "La característica ya está agregada al tour");
            }
        } catch (Exception e) {
            return new ApiResponseDTO(new Meta(UUID.randomUUID().toString(), "Error", HttpStatus.UNPROCESSABLE_ENTITY.value()), e);
        }
    }

    @Operation(summary = "Eliminar una característica de un tour", description = "Este endpoint permite eliminar una característica de un tour.")
    @DeleteMapping(value = "/v1/tours/{idTour:\\d{1,19}}/features/{idFeature:\\d{1,19}}")
    public ApiResponseDTO deleteProductFeature(@Parameter(description = "ID del tour") @PathVariable Long idTour,
            @Parameter(description = "ID de la característica") @PathVariable Long idFeature) {
        try {
            productFeatureService.deleteProductFeature(idTour, idFeature);
            return new ApiResponseDTO(meta, "Característica eliminada correctamente");
        } catch (Exception e) {
            return new ApiResponseDTO(new Meta(UUID.randomUUID().toString(), "Error", HttpStatus.UNPROCESSABLE_ENTITY.value()), e);
        }

    }
    
}
