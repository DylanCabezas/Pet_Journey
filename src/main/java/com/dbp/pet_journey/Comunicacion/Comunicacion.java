package com.dbp.pet_journey.Comunicacion;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity

public class Comunicacion {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String mensaje;

    private LocalDate fecha_ingreso;

    private LocalDate fecha_salida;
}
