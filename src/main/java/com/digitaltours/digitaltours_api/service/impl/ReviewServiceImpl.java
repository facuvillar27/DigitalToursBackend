package com.digitaltours.digitaltours_api.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.digitaltours.digitaltours_api.dto.ReviewDTO;
import com.digitaltours.digitaltours_api.entities.ReviewEntity;
import com.digitaltours.digitaltours_api.exceptions.ResourceNotFoundException;
import com.digitaltours.digitaltours_api.mappers.ReviewMapper;
import com.digitaltours.digitaltours_api.repository.ReviewRepository;
import com.digitaltours.digitaltours_api.service.ReviewService;

@Service
public class ReviewServiceImpl implements ReviewService{

    @Autowired
    private ReviewRepository reviewRepository;



    @Override
    public List<ReviewDTO> getAllReviews() {

        try {
            return reviewRepository.findAll().stream()
                    .map(ReviewMapper::mapReview)
                    .toList();
        } catch (Exception e) {
            throw new RuntimeException("Error al recuperar las reviews: " + e.getMessage(), e);
        }
    }

    @Override
    public ReviewDTO saveReview(ReviewDTO newreview) {
        
        final ReviewEntity review = ReviewMapper.mapReviewDTO(newreview);

        return ReviewMapper.mapReview(reviewRepository.save(review));
    }

    @Override
    public ReviewDTO getReview(Long id) {
        return reviewRepository.findById(id)
                .map(ReviewMapper::mapReview)
                .orElseThrow(() -> new ResourceNotFoundException("Review no encontrado"));
    }

    @Override
    public ReviewDTO deleteReview(final Long user) {
        ReviewDTO reviewEliminada = null;
        final Optional<ReviewEntity> existeReview = reviewRepository.findById(user);

        if (existeReview.isPresent()) {
            reviewEliminada = ReviewMapper.mapReview(existeReview.get());
            this.reviewRepository.delete(existeReview.get());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Review No Encontrado");
        }
        return reviewEliminada;
    }
    
}
