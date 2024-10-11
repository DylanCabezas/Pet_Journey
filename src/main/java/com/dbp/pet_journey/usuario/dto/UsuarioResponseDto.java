package com.dbp.pet_journey.usuario.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UsuarioResponseDto {
    private String name;
    private String password;
    private String email;
    private String phoneNumber;
}
