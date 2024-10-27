package com.digitaltours.digitaltours_api.service.impl;

import com.digitaltours.digitaltours_api.dto.ImageDTO;
import com.digitaltours.digitaltours_api.exceptions.ResourceNotFoundException;
import com.digitaltours.digitaltours_api.mappers.ImageMapper;
import com.digitaltours.digitaltours_api.entities.ImageEntity;
import com.digitaltours.digitaltours_api.repository.ImageRepository;
import com.digitaltours.digitaltours_api.service.ImageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    private ImageRepository imageRepository;

    @Override
    public ImageDTO uploadImage(MultipartFile file) throws IOException {
        String imageName = file.getOriginalFilename();
        if (imageName == null) {
            throw new ResourceNotFoundException("No se pudo obtener el nombre del archivo");
        }
        // Lógica para subir el archivo a S3 y obtener la URL
        String imageUrl = "url_de_la_imagen_en_s3";

        // Aqui se crea un new ImageDTO usando el constructor especifico sin id
        ImageEntity entity = ImageMapper.mapImageDTO(new ImageDTO(imageName, imageUrl));

        // Convertir la entidad guardada a DTO y retornar
        return ImageMapper.mapImage(imageRepository.save(entity));
    }

    @Override
    public List<ImageDTO> getAllImages() {
        try {
            return imageRepository.findAll().stream()
                    .map(ImageMapper::mapImage)
                    .toList();
        } catch (Exception e) {
            throw new RuntimeException("Error al recuperar las imágenes: " + e.getMessage(), e);
        }
    }
}
