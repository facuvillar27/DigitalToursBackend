package com.digitaltours.digitaltours_api.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.digitaltours.digitaltours_api.dto.ApiResponseDTO;
import com.digitaltours.digitaltours_api.service.CityService;
import com.digitaltours.digitaltours_api.utils.Meta;
import com.digitaltours.digitaltours_api.utils.ProductMessages;

import io.swagger.v3.oas.annotations.Operation;

@SuppressWarnings("rawtypes")
@Validated
@CrossOrigin
@RestController
public class CityController {

    @Autowired
    private CityService cityService;

    private final Meta meta = new Meta(UUID.randomUUID().toString(), "OK", 200);

    @Operation(summary = "Mostrar todas las ciudades existentes", description = "Este endpoint permite mostrar todas las ciudades.")
    @GetMapping("/v1/city")
    public ApiResponseDTO getCities() {
        try {
            ApiResponseDTO response = new ApiResponseDTO(meta, cityService.getAllCities());
            return response;
        } catch (Exception e) {
            return new ApiResponseDTO(new Meta(UUID.randomUUID().toString(), "Error", HttpStatus.NOT_FOUND.value()), e);
        }
    }

    @Operation(summary = "Mostrar una ciudad usando su id", description = "Este endpoint permite mostrar una ciudad usando su id.")
    @GetMapping(value = "/v1/city/{id:\\d{1,19}}")
    public ApiResponseDTO getCity(@PathVariable Long id) {
        try {
            ApiResponseDTO response = new ApiResponseDTO(meta, cityService.getCity(id));
            return response;
        } catch (Exception e) {
            return new ApiResponseDTO(new Meta(UUID.randomUUID().toString(), "Error", HttpStatus.NOT_FOUND.value()), "Ciudad No Encontrada");
        }
    }

    @Operation(summary = "Mostrar ciudades de un pais usando su id", description = "Este endpoint permite mostrar ciudades de un pais usando su id.")
    @GetMapping(value = "/v1/city/country/{id:\\d{1,19}}")
    public ApiResponseDTO getCitiesByCountryId(@PathVariable Long id) {
        try {
            ApiResponseDTO response = new ApiResponseDTO(meta, cityService.getCitiesByCountryId(id));
            return response;
        } catch (Exception e) {
            return new ApiResponseDTO(new Meta(UUID.randomUUID().toString(), "Error", HttpStatus.NOT_FOUND.value()), "Ciudades No Encontradas");
        }
    }

}
