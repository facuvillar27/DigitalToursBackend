package com.digitaltours.digitaltours_api.repository;


import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.digitaltours.digitaltours_api.entities.DatesEntity;


@Repository
public interface DatesRepository extends JpaRepository<DatesEntity, Long>{

    List<DatesEntity> findByProductId(Long productId); 
    List<DatesEntity> findByDateBetween(LocalDate startDate, LocalDate endDate);
    
}
