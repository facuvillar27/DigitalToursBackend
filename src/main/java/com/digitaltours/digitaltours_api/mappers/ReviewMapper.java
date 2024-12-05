package com.digitaltours.digitaltours_api.mappers;

import org.modelmapper.ModelMapper;

import com.digitaltours.digitaltours_api.dto.ReviewDTO;
import com.digitaltours.digitaltours_api.entities.ReviewEntity;

public class ReviewMapper {

    private ReviewMapper() {
    throw new IllegalStateException("No existe un constructor para la clase");
    }

    private static final ModelMapper MAPPER = new ModelMapper();

    public static ReviewDTO mapReview (ReviewEntity review) {
        return MAPPER.map(review, ReviewDTO.class);
    }

    public static ReviewEntity mapReviewDTO(ReviewDTO reviewDTO) {
        return MAPPER.map(reviewDTO, ReviewEntity.class);
    }
    
}
