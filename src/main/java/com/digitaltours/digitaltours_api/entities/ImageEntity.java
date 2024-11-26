package com.digitaltours.digitaltours_api.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "imagenes")
@Data
public class ImageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_image")
    private Long idImagen;

    @Column(name = "id_tour", nullable = false)
    private Long idProducto;

    @Column(name = "image_url", nullable = false, length = 200)
    private String urlImagen;
}