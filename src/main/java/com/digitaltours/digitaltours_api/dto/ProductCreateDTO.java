package com.digitaltours.digitaltours_api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductCreateDTO {

    private Long id;
    private String name;
    private String description;
    private Long categoryId;
    private Float price;
    private Integer duration;
    private Long cityId;
    private String imageUrl;
}
