package com.digitaltours.digitaltours_api.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewDTO {
    
    private Long id;
    private Integer rating;
    private String comment;
    private LocalDate review_date;
    private Long id_user;
    private Long id_tour;
}
