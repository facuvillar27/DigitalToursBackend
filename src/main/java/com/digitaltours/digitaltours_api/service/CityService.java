package com.digitaltours.digitaltours_api.service;

import java.util.List;

import com.digitaltours.digitaltours_api.dto.CityDTO;

public interface CityService {

    public List<CityDTO> getAllCities();
    public CityDTO getCity(Long id);
    public List<CityDTO> getCitiesByCountryId(Long id);
}
