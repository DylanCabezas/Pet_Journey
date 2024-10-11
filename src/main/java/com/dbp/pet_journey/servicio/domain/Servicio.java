package com.dbp.pet_journey.servicio.domain;

import com.dbp.pet_journey.mascota.domain.Mascota;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Entity
public class Servicio {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    private String nombre;

    private String description;

    private Double price;

    private EstadoServicio estado;

    @ManyToMany
    private List<Mascota> mascotas;
}
