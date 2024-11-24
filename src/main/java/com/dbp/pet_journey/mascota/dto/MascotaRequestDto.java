package com.dbp.pet_journey.mascota.dto;

import com.dbp.pet_journey.mascota.domain.Especie;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


import java.time.LocalDate;
@Getter
@Setter
public class MascotaRequestDto {
    @Size(min = 1, max = 50)
    @NotNull
    private String name;

    @Size(min = 5, max = 60)
    @NotNull
    private String breed;

    @NotNull
    private Especie especie;

    @Min(value = 0)
    @NotNull
    private Double weight;

    @Size(min = 1, max = 50)
    @NotNull
    private String caracteristics;

    @NotNull
    private LocalDate fecha_nacimiento;
}
