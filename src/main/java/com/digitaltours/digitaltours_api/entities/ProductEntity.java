package com.digitaltours.digitaltours_api.entities;

import java.io.Serializable;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tours")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductEntity implements Serializable {
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tour")
    private Long id;

    @Column(name = "nombre")
    private String name;

    @Column(name = "descripcion")
    private String description;

    @Column(name = "precio")
    private Float price;

    @Column(name = "duracion")
    private String duration;

    @ManyToOne
    @JoinColumn(name = "id_ciudad", nullable = false)
    private CityEntity  city;

    @Column(name = "id_pais")
    private String  id_country;

    @ManyToOne
    @JoinColumn(name = "id_categoria", nullable = false)
    private CategoryEntity category;

    // @Column(name = "imagen")
    // private String image;
}