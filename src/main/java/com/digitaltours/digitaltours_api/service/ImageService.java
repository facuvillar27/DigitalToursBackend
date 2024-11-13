package com.digitaltours.digitaltours_api.service;

import com.digitaltours.digitaltours_api.dto.ImageDTO;

import java.util.List;

public interface ImageService {

    ImageDTO createImage(ImageDTO imageDTO);
    List<ImageDTO> getAllImages();
    ImageDTO getImageById(Long id);
    ImageDTO updateImage(Long id, ImageDTO imageDTO);
    void deleteImage(Long id);
}