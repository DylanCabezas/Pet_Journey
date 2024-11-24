package com.dbp.pet_journey.mascota.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MascotaUpdateRequestDto {
    @NotNull
    private Double weight;

    @Size(max = 200)
    @NotNull
    private String caracteristics;
}
