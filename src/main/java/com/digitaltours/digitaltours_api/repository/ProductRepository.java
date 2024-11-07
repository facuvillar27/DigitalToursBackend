package com.digitaltours.digitaltours_api.repository;

import com.digitaltours.digitaltours_api.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    // Puedes agregar métodos personalizados aquí si es necesario

     @Query(value = "SELECT COALESCE(MAX(id_producto),0)+1 FROM producto", nativeQuery = true)
    public Integer findMaxIdProduct();
}