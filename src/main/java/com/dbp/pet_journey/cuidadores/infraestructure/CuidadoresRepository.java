package com.dbp.pet_journey.cuidadores.infraestructure;

import com.dbp.pet_journey.cuidadores.domain.Cuidadores;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CuidadoresRepository extends JpaRepository<Cuidadores, Long> {
}