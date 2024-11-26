package com.digitaltours.digitaltours_api.mappers;

import org.modelmapper.ModelMapper;

import com.digitaltours.digitaltours_api.dto.DatesDTO;
import com.digitaltours.digitaltours_api.entities.DatesEntity;

public class DatesMapper {
    
    private DatesMapper() {
        throw new IllegalStateException("No existe un constructor para la clase");
    }

    private static final ModelMapper MAPPER = new ModelMapper();

    public static DatesDTO mapDates (DatesEntity date) {
        return MAPPER.map(date, DatesDTO.class);
    }

    public static DatesEntity mapDatesDTO(DatesDTO datesDTO) {
        return MAPPER.map(datesDTO, DatesEntity.class);
    }
    
}
