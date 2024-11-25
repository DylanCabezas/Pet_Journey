package com.dbp.pet_journey.mascota.dto;

import com.dbp.pet_journey.mascota.domain.Especie;

import java.time.LocalDate;
import java.time.Period;

public class MascotaResponseDto {
    private Long id;

    private String name;

    private LocalDate fecha_nacimiento;

    private String breed;

    private String caracteristics;

    private Especie especie;

    private Integer age;

    private Double weight;

    public MascotaResponseDto(String name, LocalDate fechaNacimiento, String breed, String caracteristics) {
        this.name = name;
        this.fecha_nacimiento = fechaNacimiento;
        this.breed = breed;
        this.caracteristics = caracteristics;
        this.age = calcularEdad();
    }

    public Integer calcularEdad() {
        if (this.fecha_nacimiento == null) {
            return null;
        }
        return Period.between(this.fecha_nacimiento, LocalDate.now()).getYears();
    }

    public void setFechaNacimiento(LocalDate fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
        this.age = calcularEdad();
    }

}

