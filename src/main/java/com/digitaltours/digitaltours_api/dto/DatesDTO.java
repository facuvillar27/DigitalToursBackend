package com.digitaltours.digitaltours_api.dto;

import java.time.LocalDate;

import com.digitaltours.digitaltours_api.entities.ProductEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DatesDTO {
    
    private Long id;
    private LocalDate date;
    private Integer total_space;
    private Integer available_space;
    private ProductEntity product;
}
