package com.digitaltours.digitaltours_api.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationDTO {

    private Long id;
    private Integer numberOfPeople;
    private LocalDate reservationDate;
    private String confirmationNumber;
    private Long userId;
    private Long dateId;
    
}
