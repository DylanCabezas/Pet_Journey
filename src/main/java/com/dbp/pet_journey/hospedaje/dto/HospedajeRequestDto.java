package com.dbp.pet_journey.hospedaje.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HospedajeRequestDto {
    @Size(min = 1, max = 30)
    @NotNull
    private String name;
    @Min(value = 0)
    @Max(value = 10)
    @NotNull
    private Integer capacity;
    @Size(min = 1, max = 30)
    @NotNull
    private String location;
    @Size(min = 1, max = 20)
    @NotNull
    private String type;
}
