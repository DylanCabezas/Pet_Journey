package com.dbp.pet_journey.mascota.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class MascotaRequestDto {
    @Size(min = 1, max = 50)
    @NotNull
    private String Name;
    @Size(min = 1, max = 50)
    @NotNull
    private String Breed;
    @Min(value = 0)
    @NotNull
    private Double Weight;
    @Size(min = 1, max = 50)
    @NotNull
    private String Caracteristics;
    @Min(value = 2)
    @Max(value = 99)
    private Integer Age;
}
