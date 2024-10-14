package com.dbp.pet_journey.recomendacion.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class RecomendacionDto {
    @Size(min = 1, max = 50)
    @NotNull
    private String name;
    @Size(min = 1, max = 255)
    @NotNull
    private String description;
    @Size(min = 1, max = 20)
    @NotNull
    private String type;
    @Size(min = 1, max = 20)
    @NotNull
    private String location;

}
