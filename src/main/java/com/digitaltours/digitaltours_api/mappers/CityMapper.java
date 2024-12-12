package com.digitaltours.digitaltours_api.mappers;

import org.modelmapper.ModelMapper;

import com.digitaltours.digitaltours_api.dto.CityDTO;
import com.digitaltours.digitaltours_api.entities.CityEntity;

public class CityMapper {

    private CityMapper() {
        throw new IllegalStateException("No existe un constructor para la clase");
    }

    private static final ModelMapper MAPPER = new ModelMapper();

    public static CityDTO mapCity(CityEntity cityEntity) {
        return MAPPER.map(cityEntity, CityDTO.class);
    }

    public static CityEntity mapCityDTO(CityDTO cityDTO) {
        return MAPPER.map(cityDTO, CityEntity.class);
    }
}