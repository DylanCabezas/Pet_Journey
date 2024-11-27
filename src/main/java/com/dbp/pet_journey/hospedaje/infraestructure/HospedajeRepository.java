package com.dbp.pet_journey.hospedaje.infraestructure;

import com.dbp.pet_journey.hospedaje.domain.Hospedaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HospedajeRepository extends JpaRepository<Hospedaje, Long> {
}
