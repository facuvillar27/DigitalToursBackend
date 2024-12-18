package com.digitaltours.digitaltours_api.service;

import java.util.List;

import com.digitaltours.digitaltours_api.dto.ReservationDTO;

public interface ReservationService {

    ReservationDTO createReservation(ReservationDTO reservationDTO);

    List<ReservationDTO> getAllReservations();

    ReservationDTO getReservationById(Long id);

    List<ReservationDTO> getReservationsByUserId(Long userId);

    void deleteReservation(Long id);
    
}
