package com.dbp.pet_journey.mascota.domain;
import com.dbp.pet_journey.cuidadores.domain.Cuidadores;
import com.dbp.pet_journey.hospedaje.domain.Hospedaje;
import com.dbp.pet_journey.servicio.domain.Servicio;
import com.dbp.pet_journey.usuario.domain.Usuario;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Entity

public class Mascota {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private Especie especie;

    private Integer age;

    private double wight;

    private String caracteristics;

    private String animalBreed;

    @ManyToOne
    private Usuario usuario;

    @ManyToMany
    private List<Servicio> servicios;

    @ManyToOne
    private Hospedaje hospedaje;

    @ManyToOne
    private Cuidadores cuidador;
}