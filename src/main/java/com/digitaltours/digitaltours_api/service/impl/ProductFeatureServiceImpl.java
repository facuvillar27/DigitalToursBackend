package com.digitaltours.digitaltours_api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digitaltours.digitaltours_api.entities.FeatureEntity;
import com.digitaltours.digitaltours_api.entities.ProductEntity;
import com.digitaltours.digitaltours_api.repository.FeatureRepository;
import com.digitaltours.digitaltours_api.repository.ProductRepository;
import com.digitaltours.digitaltours_api.service.ProductFeatureService;

import jakarta.transaction.Transactional;

@Service
public class ProductFeatureServiceImpl implements ProductFeatureService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private FeatureRepository featureRepository;

    @Transactional
    public boolean addFeatureToProduct(Long idTour, Long idFeature) {
        ProductEntity product = productRepository.findById(idTour)
                .orElseThrow(() -> new IllegalArgumentException("Invalid tour ID"));
        FeatureEntity feature = featureRepository.findById(idFeature)
                .orElseThrow(() -> new IllegalArgumentException("Invalid feature ID"));

        if (product.getFeatures().contains(feature)) {
            return false;
        }

        product.getFeatures().add(feature);

        productRepository.save(product);

        return true;
    }

    @Transactional
    public void deleteProductFeature(Long idTour, Long idFeature) {
        ProductEntity product = productRepository.findById(idTour)
                .orElseThrow(() -> new IllegalArgumentException("Invalid tour ID"));
        FeatureEntity feature = featureRepository.findById(idFeature)
                .orElseThrow(() -> new IllegalArgumentException("Invalid feature ID"));

        product.getFeatures().remove(feature);

        productRepository.save(product);

    }

    
}
