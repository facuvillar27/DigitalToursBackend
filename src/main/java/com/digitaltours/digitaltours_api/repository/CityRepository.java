package com.digitaltours.digitaltours_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.digitaltours.digitaltours_api.entities.CityEntity;

public interface  CityRepository extends JpaRepository<CityEntity, Long> {
    
}
