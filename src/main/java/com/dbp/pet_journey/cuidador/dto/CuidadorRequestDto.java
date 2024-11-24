package com.dbp.pet_journey.cuidador.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class CuidadorRequestDto {
    @Size(min = 1, max = 20)
    @NotNull
    private String name;
    @Email
    @NotNull
    private String email;
    @Size(min = 1, max = 20)
    @NotNull
    private String password;
    @Size(min = 1, max = 20)
    @NotNull
    private String phoneNumber;
    @Size(min = 1, max = 255)
    @NotNull
    private String experience;
}
