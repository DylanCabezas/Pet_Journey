package com.dbp.pet_journey.usuario.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioUpdateResponseDto {
    private String username;
    private String email;
    private String phoneNumber;
    private String direction;
}
