package com.digitaltours.digitaltours_api.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "images")
@Data
public class ImageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_image")
    private Long idImagen;

    @ManyToOne
    @JoinColumn(name = "id_tour", nullable = false)
    private ProductEntity product;

    @Column(name = "image_url", nullable = false, length = 200)
    private String urlImagen;

    @Column(name = "image_principal", nullable = false)
    private boolean principal;

    public String getUrlImagen() {
        return urlImagen;
    }
}