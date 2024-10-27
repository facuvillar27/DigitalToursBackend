package com.digitaltours.digitaltours_api.repository;

import com.digitaltours.digitaltours_api.entities.ImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<ImageEntity, Long> {
    // Puedes agregar métodos personalizados aquí si es necesario
}