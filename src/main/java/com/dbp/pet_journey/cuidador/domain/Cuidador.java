package com.dbp.pet_journey.cuidador.domain;

import com.dbp.pet_journey.comunicacion.domain.Comunicacion;
import com.dbp.pet_journey.hospedaje.domain.Hospedaje;
import com.dbp.pet_journey.mascota.domain.Mascota;
import com.dbp.pet_journey.recomendacion.domain.Recomendacion;
import com.dbp.pet_journey.servicio.domain.Servicio;
import com.dbp.pet_journey.usuario.domain.Usuario;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Setter
@Getter
@Entity
public class Cuidador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    private String password;

    private String phoneNumber;

    private String experience;

    @ManyToMany
    private List<Recomendacion> recomendaciones;

    @ManyToOne
    private Hospedaje hospedaje;

    @OneToMany
    private List<Mascota> mascotas;

    @OneToMany
    private List<Servicio> servicios;
}
