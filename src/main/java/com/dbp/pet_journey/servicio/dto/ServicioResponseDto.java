package com.dbp.pet_journey.servicio.dto;

import com.dbp.pet_journey.servicio.domain.EstadoServicio;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServicioResponseDto {
    private String nombre;

    private String description;

    private Double price;

    private EstadoServicio estado;
}
