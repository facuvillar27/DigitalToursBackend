package com.digitaltours.digitaltours_api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

    private Long id;
    private String name;
    private String description;
    private CategoryDTO category; 
    private Float price;
    private Integer duration;
    private CityDTO  city;
    // private String image;
}
