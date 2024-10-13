package com.dbp.pet_journey.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class JwtAuthResponse {
    private final String token;
}
