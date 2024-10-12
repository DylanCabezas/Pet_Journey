package com.dbp.pet_journey.cuidador.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class CuidadorUpdateRequestDto {
    @NotNull
    @Email
    private String email;

    @NotNull
    @Pattern(
            regexp = "^(?=.*[A-Z])(?=.*[0-9])(?=.*[@#$%^&+=]).*$",
            message = "La contraseña debe contener al menos una letra mayúscula, un número y un carácter especial")
    private String password;

    @NotNull
    @Size(min = 9, max = 9)
    private String phoneNumber;
}
