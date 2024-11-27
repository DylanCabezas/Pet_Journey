package com.dbp.pet_journey.usuario.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioResponseDto {
    private Long id;
    private String name;
    private String username;
    private String email;
    private String phoneNumber;
}
