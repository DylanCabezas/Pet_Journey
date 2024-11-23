package com.dbp.pet_journey.usuario.domain;

import com.dbp.pet_journey.auth.infraestructure.UserAccountRepository;
import com.dbp.pet_journey.usuario.infraestructure.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UsuarioUserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UsuarioRepository repository;

    @Override
    public UserDetails loadUserByUsername(String Email) throws UsernameNotFoundException {
        return repository.findByEmail(Email);
    }

    public UserDetailsService userDetailsService() {
        return this;
    }
}