package com.dbp.pet_journey.mascota.infraestructure;

import com.dbp.pet_journey.mascota.domain.Mascota;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MascotaRepository extends JpaRepository<Mascota, Long> {
    List<Mascota> findMascotasByFechaNacimiento(LocalDate fechaNacimiento);
    Page<Mascota> findByUsuarioId(Long usuarioId, Pageable pageable);
}
