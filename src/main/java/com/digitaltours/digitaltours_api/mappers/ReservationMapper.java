package com.digitaltours.digitaltours_api.mappers;

import org.modelmapper.ModelMapper;

import com.digitaltours.digitaltours_api.dto.ReservationDTO;
import com.digitaltours.digitaltours_api.entities.ReservationEntity;

public class ReservationMapper {
    
    private ReservationMapper() {
        throw new IllegalStateException("No existe un constructor para la clase");
    }

    private static final ModelMapper MAPPER = new ModelMapper();

    public static ReservationDTO mapReservation (ReservationEntity reservation) {
        return MAPPER.map(reservation, ReservationDTO.class);
    }

    public static ReservationEntity mapReservationDTO(ReservationDTO reservationDTO) {
        return MAPPER.map(reservationDTO, ReservationEntity.class);
    }
}
