package com.dbp.pet_journey.mascota.repository;

import com.dbp.pet_journey.mascota.domain.Mascota;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MascotaRepository extends JpaRepository<Mascota, Long> {
}
