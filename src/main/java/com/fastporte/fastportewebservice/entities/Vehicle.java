package com.fastporte.fastportewebservice.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "vehicle")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Vehicle implements Serializable {

    @Id
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "driver_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @MapsId
    private Driver driver;

    @Column(name = "brand", nullable = false)
    private String brand;

    @Column(name = "photo", nullable = false)
    private String photo_car;

    @Column(name = "type_car", nullable = false)
    private String type_car;

    @Column(name = "quantity", nullable = false)
    private Long quantity;

    @Column(name = "category", nullable = false)
    private String category;
}
