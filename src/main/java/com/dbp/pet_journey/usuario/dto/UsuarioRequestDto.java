package com.dbp.pet_journey.usuario.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UsuarioRequestDto {

    @Size(min = 2, max = 20)
    @NotNull
    private String name;

    @Size(min = 2, max = 20)
    @NotNull
    private String password;

    @Email
    @NotNull
    private String email;

    @Size(min = 2, max = 20)
    @NotNull
    private String phoneNumber;

    @Size(min = 2, max = 50)
    @NotNull
    private String direction;

}
