package com.digitaltours.digitaltours_api.service;

import java.time.LocalDate;
import java.util.List;

import com.digitaltours.digitaltours_api.dto.DatesDTO;

public interface  DatesService {

    
    List<DatesDTO> getDatesByProductId(Long productId); 
    List<DatesDTO> getFilteredDates(LocalDate startDate, LocalDate endDate);
    List<DatesDTO> getFilteredDatesAlt(LocalDate date);
    DatesDTO getByDateId(Long id);

}
