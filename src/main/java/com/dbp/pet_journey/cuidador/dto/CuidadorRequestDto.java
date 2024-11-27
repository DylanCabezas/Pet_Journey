package com.dbp.pet_journey.cuidador.dto;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
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
