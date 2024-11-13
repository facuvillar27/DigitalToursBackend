package com.digitaltours.digitaltours_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.digitaltours.digitaltours_api.entities.ProductEntity;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    // Puedes agregar métodos personalizados aquí si es necesario

     @Query(value = "SELECT COALESCE(MAX(id_producto),0)+1 FROM producto", nativeQuery = true)
    public Integer findMaxIdProduct();
}