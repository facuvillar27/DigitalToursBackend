package com.digitaltours.digitaltours_api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.digitaltours.digitaltours_api.dto.CityDTO;
import com.digitaltours.digitaltours_api.exceptions.ResourceNotFoundException;
import com.digitaltours.digitaltours_api.mappers.CityMapper;
import com.digitaltours.digitaltours_api.repository.CityRepository;
import com.digitaltours.digitaltours_api.service.CityService;

public class CityServiceImpl implements CityService{

    @Autowired
    private CityRepository cityRepository;

    @Override
    public CityDTO getCity(Long id) {
        return cityRepository.findById(id)
                .map(CityMapper::mapCity)
                .orElseThrow(() -> new ResourceNotFoundException("City no encontrada"));
    }
    
}
