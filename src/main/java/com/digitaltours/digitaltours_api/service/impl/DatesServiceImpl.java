package com.digitaltours.digitaltours_api.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digitaltours.digitaltours_api.dto.DatesDTO;
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
    
}
