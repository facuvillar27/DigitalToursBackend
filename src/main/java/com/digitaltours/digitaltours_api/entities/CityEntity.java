package com.digitaltours.digitaltours_api.entities;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
@Table(name = "cities")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CityEntity implements Serializable {
    
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_city")
    private Long id;

    @Column(name = "name")
    private String name;

    // @Column(name = "id_country")
    // private String  id_country;
    
    @ManyToOne(fetch = FetchType.LAZY)  
    @JoinColumn(name = "id_country", nullable = false)
    @JsonBackReference 
    private CountryEntity country;
}
