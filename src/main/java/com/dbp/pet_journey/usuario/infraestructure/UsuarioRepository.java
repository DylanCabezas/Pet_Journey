package com.dbp.pet_journey.usuario.infraestructure;

import com.dbp.pet_journey.usuario.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}