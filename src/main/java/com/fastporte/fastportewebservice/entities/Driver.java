package com.fastporte.fastportewebservice.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "drivers")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Driver implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "lastname", nullable = false, length = 100)
    private String lastname;

    @Column(name = "username", nullable = false, length = 100)
    private String username;

    @Column(name = "photo")
    private String photo;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "phone", nullable = false, length = 9)
    private String phone;

    @Column(name = "region", nullable = false)
    private String region;

    @Temporal(TemporalType.DATE)
    @Column(name = "birthdate", nullable = false)
    private Date birthdate;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "description", nullable = false)
    private String description;
}
