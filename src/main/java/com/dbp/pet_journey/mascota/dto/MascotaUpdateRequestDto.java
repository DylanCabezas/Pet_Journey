package com.dbp.pet_journey.mascota.dto;

import com.dbp.pet_journey.mascota.domain.Especie;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class MascotaUpdateRequestDto {
    @Size(min = 1, max = 50)
    private String name;

    @Size(min = 5, max = 60)
    private String breed;

    private Especie especie;

    @NotNull
    private Double weight;

    @Size(max = 200)
    @NotNull
    private String caracteristics;

    private LocalDate fecha_nacimiento;
}