package com.digitaltours.digitaltours_api.service;

import java.util.List;

import com.digitaltours.digitaltours_api.dto.ReviewDTO;

public interface ReviewService {
    
    List<ReviewDTO> getAllReviews();
    ReviewDTO saveReview(ReviewDTO review);
    ReviewDTO getReview(Long id);
    ReviewDTO deleteReview(Long id);
}
