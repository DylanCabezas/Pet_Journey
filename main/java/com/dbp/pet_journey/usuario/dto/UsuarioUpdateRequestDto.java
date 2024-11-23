package com.dbp.pet_journey.usuario.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioUpdateRequestDto {

    @NotNull
    @Size(min = 5 , max = 20)
    private String username;

    @Email
    @NotNull
    private String email;

    @Size(min = 8, max = 20, message = "La contraseña debe tener entre 8 y 20 caracteres")
    @NotNull
    @Pattern(
            regexp = "^(?=.*[A-Z])(?=.*[0-9])(?=.*[@#$%^&+=]).*$",
            message = "La contraseña debe contener al menos una letra mayúscula, un número y un carácter especial")
    private String password;

    @Size(min = 9, max = 9)
    @NotNull
    private String celular;

    @Size(min = 5, max = 100)
    @NotNull
    private String direccion;


}

