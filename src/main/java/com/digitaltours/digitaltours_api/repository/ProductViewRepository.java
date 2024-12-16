package com.digitaltours.digitaltours_api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.digitaltours.digitaltours_api.entities.ProductViewEntity;

@Repository
public interface ProductViewRepository extends JpaRepository<ProductViewEntity, Long> {

    @Query(value = "SELECT id_tour,tour_name, category, description, price, main_image FROM digital_toursV2.home_tours_view;", nativeQuery=true)
    List<ProductViewEntity> findAllTours();

}