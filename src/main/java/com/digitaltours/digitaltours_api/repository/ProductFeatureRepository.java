package com.digitaltours.digitaltours_api.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;

@Repository
public interface ProductFeatureRepository extends JpaRepository<ProductFeature, Long> {
    
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM tours_feature WHERE id_tour = :idTour AND id_feature = :idFeature", nativeQuery = true)
    void deleteProductFeature(Long idTour, Long idFeature);

}
