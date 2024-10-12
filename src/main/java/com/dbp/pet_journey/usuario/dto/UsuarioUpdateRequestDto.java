package com.dbp.pet_journey.usuario.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioUpdateRequestDto {

    @Email
    @NotNull
    private String email;

    @Size(min = 9, max = 9)
    @NotNull
    private String celular;

    @Size(min = 5, max = 100)
    @NotNull
    private String direccion;


}

