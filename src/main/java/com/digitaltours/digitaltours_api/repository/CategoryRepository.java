package com.digitaltours.digitaltours_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.digitaltours.digitaltours_api.entities.CategoryEntity;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {

     @Query(value = "SELECT COALESCE(MAX(id_categoria),0)+1 FROM categoria", nativeQuery = true)
    public Integer findMaxIdCategory();
    
}
