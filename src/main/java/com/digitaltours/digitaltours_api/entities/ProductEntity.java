package com.digitaltours.digitaltours_api.entities;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private Float price;

    @Column(name = "hours_duration")
    private Integer duration;

    @Column(name = "start_time", nullable = false)
    @Schema(type = "string", example = "12:00:00")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
    private String startTime;

    @Column(name = "departure_time", nullable = false)
    @Schema(type = "string", example = "12:00:00")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
    private String departureTime;


    @ManyToOne
    @JoinColumn(name = "id_city", nullable = false)
    private CityEntity  city;

    @ManyToOne
    @JoinColumn(name = "id_category", nullable = false)
    private CategoryEntity category;

    @JsonIgnore
    @OneToMany(mappedBy = "product")
    private Set<DatesEntity> dates;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    private List<ImageEntity> images;

    @ManyToMany
    @JoinTable(
        name = "tours_features",
        joinColumns = @JoinColumn(name = "id_tour"),
        inverseJoinColumns = @JoinColumn(name = "id_feature")
    )
    private List<FeatureEntity> features;
}