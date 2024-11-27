package com.dbp.pet_journey.mascota.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MascotaUpdateResponseDto {
    private String name;

    private String breed;

    private String caracteristics;

    private Integer weight;

    private Integer age;
}
