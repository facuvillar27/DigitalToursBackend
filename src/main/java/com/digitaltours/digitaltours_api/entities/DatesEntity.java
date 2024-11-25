package com.digitaltours.digitaltours_api.entities;

import java.io.Serializable;
import java.time.LocalDate;

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
@Table(name = "available_dates")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class DatesEntity implements Serializable {

    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_date")
    private Long id;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "total_quota")
    private Integer total_space;

    @Column(name = "available_quota")
    private Integer available_space;

    @ManyToOne
    @JoinColumn(name = "id_tour", nullable = false)
    private ProductEntity product;
}
