package com.dbp.pet_journey.servicio.infraestructure;

import com.dbp.pet_journey.servicio.domain.Servicio;
import org.springframework.data.jpa.repository.JpaRepository;
import com.dbp.pet_journey.cuidador.domain.Cuidador;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ServicioRepository extends JpaRepository<Servicio, Long> {
    Page<Servicio> findByCuidador(Cuidador cuidador, Pageable pageable);
}