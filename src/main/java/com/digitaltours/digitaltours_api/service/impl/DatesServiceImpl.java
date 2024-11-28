package com.digitaltours.digitaltours_api.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public List<DatesDTO> getDatesByProductId(Long productId) {
        return datesRepository.findByProductId(productId).stream()
                .map(DatesMapper::mapDates)
                .collect(Collectors.toList());
    }

    @Override
    public List<DatesDTO> getFilteredDates(LocalDate startDate, LocalDate endDate) {
        return datesRepository.findByDateBetween(startDate, endDate).stream()
                .map(DatesMapper::mapDates)
                .collect(Collectors.toList());
    }

    @Override
    public List<DatesDTO> getFilteredDatesAlt(LocalDate date) {
        return datesRepository.findByDate(date).stream()
                .map(DatesMapper::mapDates)
                .collect(Collectors.toList());
    }

    @Override
    public DatesDTO getByDateId(Long id) {
        Optional<DatesEntity> dateEntity = datesRepository.findById(id);
        return dateEntity.map(DatesMapper::mapDates)
                         .orElseThrow(() -> new RuntimeException("Fecha no encontrada"));
    }
    
}
