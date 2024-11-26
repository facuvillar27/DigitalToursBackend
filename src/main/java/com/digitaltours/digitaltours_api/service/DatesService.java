package com.digitaltours.digitaltours_api.service;

import java.util.List;

import com.digitaltours.digitaltours_api.dto.DatesDTO;

public interface  DatesService {

    
    List<DatesDTO> getDatesByProductId(Long productId); 
    
}
