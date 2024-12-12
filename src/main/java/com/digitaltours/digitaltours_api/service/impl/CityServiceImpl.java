package com.digitaltours.digitaltours_api.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.digitaltours.digitaltours_api.dto.CityDTO;
import com.digitaltours.digitaltours_api.entities.CityEntity;
import com.digitaltours.digitaltours_api.mappers.CityMapper;
import com.digitaltours.digitaltours_api.repository.CityRepository;
import com.digitaltours.digitaltours_api.service.CityService;

@Service
public class CityServiceImpl implements CityService {

    @Autowired
    private CityRepository cityRepository;


    @Override
    public List<CityDTO> getAllCities() {

        try {
            return cityRepository.findAll().stream()
                    .map(CityMapper::mapCity)
                    .toList();
        } catch (Exception e) {
            throw new RuntimeException("Error al recuperar los paises: " + e.getMessage(), e);
        }
    }

    @Override
    public CityDTO getCity(final Long id) {
        final Optional<CityEntity> existCity = cityRepository.findById(id);
        CityDTO cityDTO = null;

        if (existCity.isPresent()) {
            cityDTO = CityMapper.mapCity(existCity.get());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ciudad No Encontrada");
        }
        return cityDTO;
    }

    @Override
public List<CityDTO> getCitiesByCountryId(final Long countryId) {
    try {
        List<CityEntity> cities = cityRepository.findByCountryId(countryId);

        if (cities.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontraron ciudades para el país con ID: " + countryId);
        }

        return cities.stream()
                .map(CityMapper::mapCity)
                .toList();

    } catch (Exception e) {
        throw new RuntimeException("Error al recuperar las ciudades: " + e.getMessage(), e);
    }
}

}