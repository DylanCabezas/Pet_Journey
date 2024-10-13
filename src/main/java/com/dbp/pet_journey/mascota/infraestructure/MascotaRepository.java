package com.dbp.pet_journey.mascota.infraestructure;

import com.dbp.pet_journey.mascota.domain.Mascota;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface MascotaRepository extends JpaRepository<Mascota, Long> {
    List<Mascota> findMascotasByFechaNacimiento(LocalDate fechaNacimiento);
}