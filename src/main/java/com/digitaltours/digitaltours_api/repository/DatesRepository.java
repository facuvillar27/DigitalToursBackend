package com.digitaltours.digitaltours_api.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.digitaltours.digitaltours_api.entities.DatesEntity;


@Repository
public interface DatesRepository extends JpaRepository<DatesEntity, Long>{
    
}
