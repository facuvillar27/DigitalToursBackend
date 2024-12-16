package com.digitaltours.digitaltours_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.digitaltours.digitaltours_api.entities.FeatureEntity;

@Repository
public interface FeatureRepository extends JpaRepository<FeatureEntity, Long> {

}
