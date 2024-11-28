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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.digitaltours.digitaltours_api.dto.ApiResponseDTO;
import com.digitaltours.digitaltours_api.dto.FeatureDTO;
import com.digitaltours.digitaltours_api.service.FeatureService;
import com.digitaltours.digitaltours_api.utils.FeatureMessages;
import com.digitaltours.digitaltours_api.utils.Meta;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;

@Validated
@CrossOrigin
@RestController
public class FeatureController {

    @Autowired
    private FeatureService featureService;

    private final Meta meta = new Meta(UUID.randomUUID().toString(), "OK", 200);
    
    @Operation(summary = "Mostrar todas las características existentes", description = "Este endpoint permite mostrar todas las características registradas.")
    @GetMapping("/v1/features")
    public ApiResponseDTO getAllFeatures() {
        try {
            ApiResponseDTO response = new ApiResponseDTO(meta, featureService.getAllFeatures());
            return response;
        } catch (Exception e) {
            return new ApiResponseDTO(new Meta(UUID.randomUUID().toString(), "Error", HttpStatus.NOT_FOUND.value()), e);
        }
    }

    @Operation(summary = "Mostrar una característica usando su id", description = "Este endpoint permite mostrar una característica utilizando su id.")
    @GetMapping(value = "/v1/features/{id:\\d{1,19}}")
    public ApiResponseDTO getFeatureById(@Parameter(description = "ID de la característica") @PathVariable Long id) {
        try {
            ApiResponseDTO response = new ApiResponseDTO(meta, featureService.getFeatureById(id));
            return response;
        } catch (Exception e) {
            return new ApiResponseDTO(new Meta(UUID.randomUUID().toString(), "Error", HttpStatus.NOT_FOUND.value()), FeatureMessages.FEATURE_NOT_FOUND);
        }
    }

    @Operation(summary = "Guardar una nueva característica", description = "Este endpoint le permite a un administrador guardar una nueva característica.")
    @PostMapping(path = "/v1/features")
    public ApiResponseDTO saveFeature(@RequestBody @Validated FeatureDTO featureDTO) {
        try {
            ApiResponseDTO response = new ApiResponseDTO(meta, featureService.createFeature(featureDTO));
            return response;
        } catch (Exception e) {
            return new ApiResponseDTO(new Meta(UUID.randomUUID().toString(), "Error", HttpStatus.UNPROCESSABLE_ENTITY.value()), e);
        }
    }

    @Operation(summary = "Actualizar una característica existente", description = "Este endpoint le permite a un administrador actualizar una característica existente.")
    @PutMapping(path = "/v1/features/{id:\\d{1,19}}")
    public ApiResponseDTO updateFeature(@Parameter(description = "ID de la característica") @PathVariable Long id, @RequestBody @Validated FeatureDTO featureDTO) {
        try {
            ApiResponseDTO response = new ApiResponseDTO(meta, featureService.updateFeature(id, featureDTO));
            return response;
        } catch (Exception e) {
            return new ApiResponseDTO(new Meta(UUID.randomUUID().toString(), "Error", HttpStatus.UNPROCESSABLE_ENTITY.value()), e);
        }
    }

    @Operation(summary = "Eliminar una característica existente", description = "Este endpoint le permite a un administrador eliminar una característica existente.")
    @DeleteMapping(path = "/v1/features/{id:\\d{1,19}}")
    public ApiResponseDTO deleteFeature(@Parameter(description = "ID de la característica") @PathVariable Long id) {
        try {
            featureService.deleteFeature(id);
            return new ApiResponseDTO(meta, "Feature deleted successfully");
        } catch (Exception e) {
            return new ApiResponseDTO(new Meta(UUID.randomUUID().toString(), "Error", HttpStatus.UNPROCESSABLE_ENTITY.value()), e);
        }
    }
}
