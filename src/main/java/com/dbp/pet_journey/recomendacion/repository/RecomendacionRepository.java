package com.dbp.pet_journey.recomendacion.repository;

import com.dbp.pet_journey.recomendacion.domain.Recomendacion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecomendacionRepository extends JpaRepository<Recomendacion, Long> {
}
