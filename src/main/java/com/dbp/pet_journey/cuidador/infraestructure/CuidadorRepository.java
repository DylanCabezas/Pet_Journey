package com.dbp.pet_journey.cuidador.infraestructure;

import com.dbp.pet_journey.cuidador.domain.Cuidador;
import com.dbp.pet_journey.usuario.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CuidadorRepository extends JpaRepository<Cuidador, Long> {
    Cuidador findByEmail(String email);
}
