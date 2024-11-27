package com.dbp.pet_journey.recomendacion.infraestructure;

import com.dbp.pet_journey.cuidador.domain.Cuidador;
import com.dbp.pet_journey.recomendacion.domain.Recomendacion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecomendacionRepository extends JpaRepository<Recomendacion, Long> {
    Page<Recomendacion> findByCuidador(Cuidador cuidador, Pageable pageable);
}
