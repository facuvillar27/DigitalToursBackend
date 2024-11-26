package com.digitaltours.digitaltours_api.dto;

import com.digitaltours.digitaltours_api.entities.CountryEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CityDTO {

    private Long id;
    private String name;
    private CountryEntity country;
}
