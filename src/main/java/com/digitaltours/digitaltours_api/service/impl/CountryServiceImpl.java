package com.digitaltours.digitaltours_api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digitaltours.digitaltours_api.dto.CountryDTO;
import com.digitaltours.digitaltours_api.mappers.CountryMapper;
import com.digitaltours.digitaltours_api.repository.CountryRepository;
import com.digitaltours.digitaltours_api.service.CountryService;

@Service
public class CountryServiceImpl implements CountryService {

    @Autowired
    private CountryRepository countryRepository;


    @Override
    public List<CountryDTO> getAllCountries() {

        try {
            return countryRepository.findAll().stream()
                    .map(CountryMapper::mapCountry)
                    .toList();
        } catch (Exception e) {
            throw new RuntimeException("Error al recuperar los paises: " + e.getMessage(), e);
        }
    }

}