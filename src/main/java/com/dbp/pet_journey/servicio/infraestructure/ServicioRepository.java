package com.dbp.pet_journey.servicio.infraestructure;

import com.dbp.pet_journey.servicio.domain.Servicio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServicioRepository extends JpaRepository<Servicio, Long> {
}