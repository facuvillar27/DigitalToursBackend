package com.digitaltours.digitaltours_api.controller;

import java.time.LocalDate;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.digitaltours.digitaltours_api.dto.ApiResponseDTO;
import com.digitaltours.digitaltours_api.service.DatesService;
import com.digitaltours.digitaltours_api.utils.CategoryMessages;
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

    @Operation(summary = "Mostrar las fechas disponibles de un tour", description = "Este endpoint permite mostrar todas las fechas disponibles asociadas a un tour usando su id.")
    @GetMapping("/v1/dates/{id:[\\d]+}")
    public ApiResponseDTO getDatesByProductId(@PathVariable Long id) {
        try {
            ApiResponseDTO response = new ApiResponseDTO(meta, datesService.getDatesByProductId(id));
            return response;
        } catch (Exception e) {
            return new ApiResponseDTO(new Meta(UUID.randomUUID().toString(), "Error", HttpStatus.NOT_FOUND.value()), CategoryMessages.CATEGORY_NOT_FOUND);
        }
    }

    @Operation(summary = "Filtrar fechas disponibles por rango", description = "Este endpoint permite filtrar las fechas disponibles de un tour usando un rango de fechas.")
    @GetMapping("/v1/dates/filter")
    public ApiResponseDTO getFilteredDates(
            @RequestParam("startDate") String startDate,
            @RequestParam("endDate") String endDate) {

        try {
            LocalDate start = LocalDate.parse(startDate);  // Convertir la fecha de inicio a LocalDate
            LocalDate end = LocalDate.parse(endDate);      // Convertir la fecha de fin a LocalDate
            
            ApiResponseDTO response = new ApiResponseDTO(meta, datesService.getFilteredDates(start, end));
            return response;
        } catch (Exception e) {
            return new ApiResponseDTO(new Meta(UUID.randomUUID().toString(), "Error", HttpStatus.NOT_FOUND.value()), "Error al filtrar las fechas.");
        }
    }

    @Operation(summary = "Filtrar fechas disponibles", description = "Este endpoint permite filtrar las fechas disponibles de un tour.")
    @GetMapping("/v1/dates/filtealt")
    public ApiResponseDTO getFilteredDatesAlt(
            @RequestParam("date") String date) {

        try {
            LocalDate datealt = LocalDate.parse(date);
            
            ApiResponseDTO response = new ApiResponseDTO(meta, datesService.getFilteredDatesAlt(datealt));
            return response;
        } catch (Exception e) {
            return new ApiResponseDTO(new Meta(UUID.randomUUID().toString(), "Error", HttpStatus.NOT_FOUND.value()), "Error al filtrar las fechas.");
        }
    }
}
