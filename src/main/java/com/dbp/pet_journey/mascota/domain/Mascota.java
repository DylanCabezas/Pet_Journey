package com.dbp.pet_journey.mascota.domain;


import com.dbp.pet_journey.cuidador.domain.Cuidador;
import com.dbp.pet_journey.hospedaje.domain.Hospedaje;
import com.dbp.pet_journey.servicio.domain.Servicio;
import com.dbp.pet_journey.usuario.domain.Usuario;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@Entity
public class Mascota {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String breed;

    private Double weight;

    private String caracteristics;

    private Integer age;

    @ManyToOne
    private Usuario usuario;

    @ManyToMany
    private List<Servicio> servicios;

    @ManyToOne
    private Hospedaje hospedaje;

    @ManyToOne
    private Cuidador cuidador;


}
