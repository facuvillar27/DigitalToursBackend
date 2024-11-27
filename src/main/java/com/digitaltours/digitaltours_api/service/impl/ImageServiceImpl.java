package com.digitaltours.digitaltours_api.service.impl;

import com.digitaltours.digitaltours_api.dto.ImageDTO;
import com.digitaltours.digitaltours_api.entities.ImageEntity;
import com.digitaltours.digitaltours_api.entities.ProductEntity;
import com.digitaltours.digitaltours_api.mappers.ImageMapper;
import com.digitaltours.digitaltours_api.repository.ImageRepository;
import com.digitaltours.digitaltours_api.service.ImageService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository;

    // Ya no necesitas inyectar ImageMapper, ya que es estático
    public ImageServiceImpl(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    @Override
    public ImageDTO createImage(ImageDTO imageDTO) {
        ImageEntity imageEntity = ImageMapper.mapImageDTO(imageDTO);  // Usa el método estático
        imageEntity = imageRepository.save(imageEntity);
        return ImageMapper.mapImage(imageEntity);  // Usa el método estático
    }

    @Override
    public List<ImageDTO> getAllImages() {
        return imageRepository.findAll().stream()
                .map(ImageMapper::mapImage)  // Usa el método estático
                .collect(Collectors.toList());
    }

    @Override
    public ImageDTO getImageById(Long id) {
        ImageEntity imageEntity = imageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Image not found"));
        return ImageMapper.mapImage(imageEntity);  // Usa el método estático
    }

    @Override
    public ImageDTO updateImage(Long id, ImageDTO imageDTO) {
        ImageEntity imageEntity = imageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Image not found"));
        ProductEntity product = new ProductEntity();
        product.setId(imageDTO.getIdProducto());
        imageEntity.setProduct(product);
        imageEntity.setUrlImagen(imageDTO.getUrlImagen());
        imageEntity = imageRepository.save(imageEntity);
        return ImageMapper.mapImage(imageEntity);  // Usa el método estático
    }

    @Override
    public void deleteImage(Long id) {
        ImageEntity imageEntity = imageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Image not found"));
        imageRepository.delete(imageEntity);
    }
}