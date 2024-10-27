package com.digitaltours.digitaltours_api.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.digitaltours.digitaltours_api.dto.ImageDTO;

public interface ImageService {

    ImageDTO uploadImage(MultipartFile file) throws IOException;
    List<ImageDTO> getAllImages();
}
