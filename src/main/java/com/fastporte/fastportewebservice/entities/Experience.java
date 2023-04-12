package com.fastporte.fastportewebservice.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Time;

@Entity
@Table(name = "experience")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Experience implements Serializable{

    @Id
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "driver_1")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @MapsId
    private Driver driver;

    @Column(name = "job", nullable = false)
    private String job;

    @Column(name = "time", nullable = false)
    // @Temporal(TemporalType.TIMESTAMP)
    private Time time;
}
