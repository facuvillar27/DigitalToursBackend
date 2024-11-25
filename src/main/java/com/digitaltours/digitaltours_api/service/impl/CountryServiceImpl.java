package com.digitaltours.digitaltours_api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.digitaltours.digitaltours_api.dto.CountryDTO;
import com.digitaltours.digitaltours_api.exceptions.ResourceNotFoundException;
import com.digitaltours.digitaltours_api.mappers.CountryMapper;
import com.digitaltours.digitaltours_api.repository.CountryRepository;
import com.digitaltours.digitaltours_api.service.CountryService;

public class CountryServiceImpl implements CountryService{
    
    @Autowired
    private CountryRepository countryRepository;

    @Override
    public CountryDTO getCountry(Long id) {
        return countryRepository.findById(id)
                .map(CountryMapper::mapCountry)
                .orElseThrow(() -> new ResourceNotFoundException("Country no encontrada"));
    }
    
}
