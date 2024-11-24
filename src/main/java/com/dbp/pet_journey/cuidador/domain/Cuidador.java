package com.dbp.pet_journey.cuidador.domain;

import com.dbp.pet_journey.auth.domain.Role;
import com.dbp.pet_journey.comunicacion.domain.Comunicacion;
import com.dbp.pet_journey.hospedaje.domain.Hospedaje;
import com.dbp.pet_journey.mascota.domain.Mascota;
import com.dbp.pet_journey.recomendacion.domain.Recomendacion;
import com.dbp.pet_journey.servicio.domain.Servicio;
import com.dbp.pet_journey.usuario.domain.Usuario;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
@Setter
@Getter
@Entity
public class Cuidador implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true)
    private String email;

    private String password;

    private String phoneNumber;

    private String experience;

    @Enumerated(EnumType.STRING)
    private Role role;


    @ManyToMany
    private List<Recomendacion> recomendaciones;

    @ManyToOne
    private Hospedaje hospedaje;

    @OneToMany
    private List<Mascota> mascotas;

    @OneToMany
    private List<Servicio> servicios;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return email; // Devuelve el email como nombre de usuario
    }

    @Override
    public String getPassword() {
        return password; // Devuelve la contraseña almacenada
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}
