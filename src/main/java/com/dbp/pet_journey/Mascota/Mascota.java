package com.dbp.pet_journey.Mascota;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity

public class Mascota {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nombre;

    private Especie especie;

    private Integer edad;

    private double peso;

    private String caracteristicas;

    private String Raza;
}