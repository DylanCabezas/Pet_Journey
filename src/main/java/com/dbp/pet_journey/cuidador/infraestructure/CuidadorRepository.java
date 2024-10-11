package com.dbp.pet_journey.cuidador.infraestructure;

import com.dbp.pet_journey.cuidador.domain.Cuidador;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CuidadorRepository extends JpaRepository<Cuidador, Integer> {
}
