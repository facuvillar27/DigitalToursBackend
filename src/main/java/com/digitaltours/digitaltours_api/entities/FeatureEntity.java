package com.digitaltours.digitaltours_api.entities;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "features")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class FeatureEntity {

    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_feature")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "urlImg", nullable = false)
    private String urlImg;
    
}
