package com.digitaltours.digitaltours_api.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.digitaltours.digitaltours_api.dto.FeatureDTO;
import com.digitaltours.digitaltours_api.entities.FeatureEntity;
import com.digitaltours.digitaltours_api.mappers.FeatureMapper;
import com.digitaltours.digitaltours_api.repository.FeatureRepository;
import com.digitaltours.digitaltours_api.service.FeatureService;

@Service
public class FeatureServiceImpl implements FeatureService {

    private final FeatureRepository featureRepository;

    public FeatureServiceImpl(FeatureRepository featureRepository) {
        this.featureRepository = featureRepository;
    }

    @Override
    public FeatureDTO createFeature(FeatureDTO featureDTO) {
        FeatureEntity featureEntity = FeatureMapper.mapFeatureDTO(featureDTO);
        featureEntity = featureRepository.save(featureEntity);
        return FeatureMapper.mapFeature(featureEntity);
    }

    @Override
    public List<FeatureDTO> getAllFeatures() {
        return featureRepository.findAll().stream()
                .map(FeatureMapper::mapFeature)
                .collect(Collectors.toList());
    }

    @Override
    public FeatureDTO getFeatureById(Long id) {
        FeatureEntity featureEntity = featureRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Feature not found"));
        return FeatureMapper.mapFeature(featureEntity);
    }

    @Override
    public FeatureDTO updateFeature(Long id, FeatureDTO featureDTO) {
        FeatureEntity featureEntity = featureRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Feature not found"));
        featureEntity.setName(featureDTO.getName());
        featureEntity.setUrlImg(featureDTO.getUrlImg());
        featureEntity = featureRepository.save(featureEntity);
        return FeatureMapper.mapFeature(featureEntity);
    }

    @Override
    public void deleteFeature(Long id) {
        FeatureEntity featureEntity = featureRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Feature not found"));
        featureRepository.delete(featureEntity);
    }

    
    
}
