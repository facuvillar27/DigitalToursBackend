package com.digitaltours.digitaltours_api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductViewDTO {

    private Long id;
    private String name;
    private String category;
    private String description;
    private Float price;
    private String mainImage;
}
