package com.digitaltours.digitaltours_api.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.digitaltours.digitaltours_api.dto.ApiResponseDTO;
import com.digitaltours.digitaltours_api.dto.ReservationDTO;
import com.digitaltours.digitaltours_api.service.ReservationService;
import com.digitaltours.digitaltours_api.utils.Meta;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
public class ReservationController {

    @Autowired
    private final ReservationService reservationService;

    private final Meta meta = new Meta(UUID.randomUUID().toString(), "OK", 200);

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @Operation(summary = "Crear una nueva reserva", description = "Este endpoint permite a un usuario crear una nueva reserva.")
    @PostMapping("/v1/reservation")
    public ApiResponseDTO createReservation(@RequestBody @Valid ReservationDTO reservationDTO) {
        try {
            ApiResponseDTO response = new ApiResponseDTO(meta, reservationService.createReservation(reservationDTO));
            return response;
        } catch (Exception e) {
            return new ApiResponseDTO(new Meta(UUID.randomUUID().toString(), "Error", HttpStatus.NOT_FOUND.value()), e);
        }
    }

    @Operation(summary = "Mostrar todas las reservas existentes", description = "Este endpoint permite mostrar todas las reservas.")
    @GetMapping("/v1/reservations")
    public ApiResponseDTO getAllReservations() {
        try {
            ApiResponseDTO response = new ApiResponseDTO(meta, reservationService.getAllReservations());
            return response;
        } catch (Exception e) {
            return new ApiResponseDTO(new Meta(UUID.randomUUID().toString(), "Error", HttpStatus.NOT_FOUND.value()), e);
        }
    }

    @Operation(summary = "Mostrar una reserva usando su id", description = "Este endpoint permite mostrar una reserva usando su id.")
    @GetMapping(value = "/v1/reservation/{id:\\d{1,19}}")
    public ApiResponseDTO getReservation(@PathVariable Long id) {
        try {
            ApiResponseDTO response = new ApiResponseDTO(meta, reservationService.getReservationById(id));
            return response;
        } catch (Exception e) {
            return new ApiResponseDTO(new Meta(UUID.randomUUID().toString(), "Error", HttpStatus.NOT_FOUND.value()), e);
        }
    }

    @Operation(summary = "Eliminar una reserva usando su id", description = "Este endpoint permite eliminar una reserva usando su id.")
    @DeleteMapping("/v1/reservation/{id:[\\d]+}")
    public ApiResponseDTO deleteReservation(@PathVariable Long id) {
        try {
            reservationService.deleteReservation(id);
            return new ApiResponseDTO(meta, "Reservation deleted");
        } catch (Exception e) {
            return new ApiResponseDTO(new Meta(UUID.randomUUID().toString(), "Error", HttpStatus.NOT_FOUND.value()), e);
        }
    }

    @Operation(summary = "Obtener todas las reservas de un usuario", description = "Este endpoint permite obtener todas las reservas de un usuario.")
    @GetMapping("/v1/reservations/user/{id:[\\d]+}")
    public ApiResponseDTO getReservationsByUser(@PathVariable Long id) {
        try {
            ApiResponseDTO response = new ApiResponseDTO(meta, reservationService.getReservationsByUserId(id));
            return response;
        } catch (Exception e) {
            return new ApiResponseDTO(new Meta(UUID.randomUUID().toString(), "Error", HttpStatus.NOT_FOUND.value()), e);
        }
    }

}

   
