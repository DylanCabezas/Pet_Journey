package com.dbp.pet_journey.comunicacion.domain;

import com.dbp.pet_journey.cuidador.domain.Cuidador;
import com.dbp.pet_journey.usuario.domain.Usuario;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity

public class Comunicacion {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private LocalDate fecha_ingreso;

    private LocalDate fecha_salida;

    private String message;

    private LocalDate sendDate;

    @ManyToMany
    private List<Usuario> usuarios;

    @ManyToMany

    private List<Cuidador> cuidadores;
}