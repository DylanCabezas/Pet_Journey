package com.dbp.pet_journey.hospedaje.domain;

import com.dbp.pet_journey.cuidador.domain.Cuidador;
import com.dbp.pet_journey.servicio.domain.Servicio;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Entity
public class Hospedaje {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Integer capacity;

    private String location;

    private String type;

    @OneToMany
    private List<Servicio> servicios;

    @OneToOne
    private Cuidador cuidador;
}
