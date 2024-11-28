package com.digitaltours.digitaltours_api.service;

public interface ProductFeatureService {


    boolean addFeatureToProduct(Long idTour, Long idFeature);
    void deleteProductFeature(Long idTour, Long idFeature);
}
