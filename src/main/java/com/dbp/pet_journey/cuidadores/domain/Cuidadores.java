package com.dbp.pet_journey.cuidadores.domain;

import com.dbp.pet_journey.comunicacion.domain.Comunicacion;
import com.dbp.pet_journey.hospedaje.domain.Hospedaje;
import com.dbp.pet_journey.mascota.domain.Mascota;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Entity

public class Cuidadores {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String email;

    private String password;

    private String phoneNumber;

    private Integer experiencia;

    @ManyToMany
    private List<Comunicacion> comunicaciones;

    @ManyToOne
    private Hospedaje hospedaje;

    @OneToMany
    private List<Mascota> mascotas;
}