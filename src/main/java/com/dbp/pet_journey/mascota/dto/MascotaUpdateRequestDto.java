package com.dbp.pet_journey.mascota.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class MascotaUpdateRequestDto {
    @NotNull
    private Double Weight;

    @Size(max = 200)
    @NotNull
    private String Caracteristics;
}
