package com.digitaltours.digitaltours_api.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.digitaltours.digitaltours_api.dto.DatesDTO;
import com.digitaltours.digitaltours_api.entities.DatesEntity;
import com.digitaltours.digitaltours_api.mappers.DatesMapper;
import com.digitaltours.digitaltours_api.repository.DatesRepository;
import com.digitaltours.digitaltours_api.service.DatesService;

@Service
public class DatesServiceImpl implements DatesService{


    @Autowired
    private DatesRepository datesRepository;


    @Override
    public DatesDTO getDates(final Long id) {
        final Optional<DatesEntity> existDates = datesRepository.findById(id);
        DatesDTO datesDTO = null;

        if (existDates.isPresent()) {
            datesDTO = DatesMapper.mapDates(existDates.get());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Producto No Encontrado");
        }
        return datesDTO;
    }

    
    @Override
    public List<DatesDTO> getAllDates() {

        try {
            return datesRepository.findAll().stream()
                    .map(DatesMapper::mapDates)
                    .toList();
        } catch (Exception e) {
            throw new RuntimeException("Error al recuperar los productos: " + e.getMessage(), e);
        }
    }
    
}
