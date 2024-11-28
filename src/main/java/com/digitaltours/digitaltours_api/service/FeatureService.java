package com.digitaltours.digitaltours_api.service;

import java.util.List;

import com.digitaltours.digitaltours_api.dto.FeatureDTO;

public interface FeatureService {

    FeatureDTO createFeature(FeatureDTO featureDTO);
    List<FeatureDTO> getAllFeatures();
    FeatureDTO getFeatureById(Long id);
    FeatureDTO updateFeature(Long id, FeatureDTO featureDTO);
    void deleteFeature(Long id);

}
