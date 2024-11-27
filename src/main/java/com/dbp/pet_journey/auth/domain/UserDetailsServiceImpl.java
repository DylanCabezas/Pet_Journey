package com.dbp.pet_journey.auth.domain;

import com.dbp.pet_journey.cuidador.domain.Cuidador;
import com.dbp.pet_journey.cuidador.infraestructure.CuidadorRepository;
import com.dbp.pet_journey.usuario.domain.Usuario;
import com.dbp.pet_journey.usuario.infraestructure.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    CuidadorRepository cuidadorRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByEmail(username);
        if (usuario != null) {
            return new org.springframework.security.core.userdetails.User(
                    usuario.getUsername(),
                    usuario.getPassword(),
                    Collections.singletonList(
                            new SimpleGrantedAuthority(usuario.getRole().getSpringSecurityRole())
                    )
            );
        }

        // Si no se encuentra en Usuarios, busca en Cuidadores
        Cuidador cuidador = cuidadorRepository.findByEmail(username);
        if (cuidador != null) {
            return new org.springframework.security.core.userdetails.User(
                    cuidador.getUsername(),
                    cuidador.getPassword(),
                    Collections.singletonList(
                            new SimpleGrantedAuthority(cuidador.getRole().getSpringSecurityRole())
                    )
            );
        }
        throw new UsernameNotFoundException("Usuario no encontrado con el email: " + username);
    }

    public UserDetailsService userDetailsService() {
        return this;
    }
}