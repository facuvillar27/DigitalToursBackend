package com.digitaltours.digitaltours_api.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FavoriteDTO {

    private Long id;
    private Long userId;
    private Long tourId;
    private LocalDate addedDate;

}
