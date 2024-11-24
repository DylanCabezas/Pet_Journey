package com.dbp.pet_journey.auth.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginReq {
    private String email;
    private String password;

    public LoginReq() {
    }

    public LoginReq(String email, String password) {
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
