package com.dbp.pet_journey.Recomendacion;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Recomendacion {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String comentario;

    private Double puntuacion;
}
