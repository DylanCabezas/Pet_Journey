package com.dbp.pet_journey.servicio.dto;

import com.dbp.pet_journey.servicio.domain.EstadoServicio;
import jakarta.validation.constraints.*;

public class ServicioRequestDto {
    @Size(min = 2, max = 30)
    @NotNull
    private String nombre;
    @Size(min = 2, max = 255)
    @NotNull
    private String description;
    @DecimalMin(value = "1.0")
    @DecimalMax(value = "500.00")
    @NotNull
    private Double price;
    @NotNull
    private EstadoServicio estado;
}
