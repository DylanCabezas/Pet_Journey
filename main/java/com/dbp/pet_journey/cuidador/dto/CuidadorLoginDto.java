package com.dbp.pet_journey.cuidador.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CuidadorLoginDto {
    private String email;
    private String password;

    public  CuidadorLoginDto() {
    }

    public  CuidadorLoginDto(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
