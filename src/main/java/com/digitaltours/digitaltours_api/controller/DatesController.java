package com.digitaltours.digitaltours_api.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.digitaltours.digitaltours_api.dto.ApiResponseDTO;
import com.digitaltours.digitaltours_api.service.DatesService;
import com.digitaltours.digitaltours_api.utils.Meta;

import io.swagger.v3.oas.annotations.Operation;


@SuppressWarnings("rawtypes")
@Validated
@CrossOrigin
@RestController
public class DatesController {
    
    @Autowired
    private DatesService datesService;

    private final Meta meta = new Meta(UUID.randomUUID().toString(), "OK", 200);

    @Operation(summary = "Mostrar todos las fechas disponibles", description = "Este endpoint permite mostrar todos las fechas disponibles.")
    @GetMapping("/v1/dates")
    public ApiResponseDTO getAllDates() {
        try {
            ApiResponseDTO response = new ApiResponseDTO(meta, datesService.getAllDates());
            return response;
        } catch (Exception e) {
            return new ApiResponseDTO(new Meta(UUID.randomUUID().toString(), "Error", HttpStatus.NOT_FOUND.value()), e);
        }
    }
}
