package com.dbp.pet_journey.comunicacion.infraestructure;


import com.dbp.pet_journey.comunicacion.domain.Comunicacion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComunicacionRepository extends JpaRepository<Comunicacion, Long> {
}