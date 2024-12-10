package com.digitaltours.digitaltours_api.dto;

import java.util.List;

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
    private String startTime;
    private String departureTime;
    private CityDTO city;
    private List<String> imageUrls;
    private List<FeatureDTO> features;
}
