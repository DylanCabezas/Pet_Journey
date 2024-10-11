package com.dbp.pet_journey.usuario.domain;

import com.dbp.pet_journey.comunicacion.domain.Comunicacion;
import com.dbp.pet_journey.mascota.domain.Mascota;
import com.dbp.pet_journey.recomendacion.domain.Recomendacion;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    private String name;

    private String password;

    private String email;

    private String phoneNumber;

    private String direction;

    @OneToMany
    private List<Recomendacion> recomendaciones;

    @ManyToMany
    private List<Comunicacion> comunicaciones;

    @OneToMany
    private List<Mascota> mascotas;
}
