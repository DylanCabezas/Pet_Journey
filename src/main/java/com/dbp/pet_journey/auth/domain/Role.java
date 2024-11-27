package com.dbp.pet_journey.auth.domain;

public enum Role {
    ADMIN("ADMIN"),
    CLIENTE("CLIENTE"),
    CUIDADOR("CUIDADOR"),;


    private final String springSecurityRole;

    Role(String springSecurityRole) {
        this.springSecurityRole = springSecurityRole;
    }

    public String getSpringSecurityRole() {
        return springSecurityRole;
    }
}
