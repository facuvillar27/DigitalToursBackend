package com.digitaltours.digitaltours_api.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "imagenes")
@Data
public class ImageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_id")
    private Long idImagen;

    @Column(name = "product_id", nullable = false)
    private Long idProducto;

    @Column(name = "image_url", nullable = false, length = 200)
    private String urlImagen;
}