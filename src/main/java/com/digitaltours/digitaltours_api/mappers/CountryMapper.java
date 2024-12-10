package com.digitaltours.digitaltours_api.mappers;

import org.modelmapper.ModelMapper;

import com.digitaltours.digitaltours_api.dto.CountryDTO;
import com.digitaltours.digitaltours_api.entities.CountryEntity;

public class CountryMapper {

    private CountryMapper() {
        throw new IllegalStateException("No existe un constructor para la clase");
    }

    private static final ModelMapper MAPPER = new ModelMapper();

    public static CountryDTO mapCountry(CountryEntity countryEntity) {
        return MAPPER.map(countryEntity, CountryDTO.class);
    }

    public static CountryEntity mapCountryDTO(CountryDTO countryDTO) {
        return MAPPER.map(countryDTO, CountryEntity.class);
    }
}