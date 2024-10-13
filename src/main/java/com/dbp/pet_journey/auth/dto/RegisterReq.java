package com.dbp.pet_journey.auth.dto;

import com.dbp.pet_journey.auth.domain.Role;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class RegisterReq {
    private String username;
    private String password;
    private Set<Role> roles;
}
