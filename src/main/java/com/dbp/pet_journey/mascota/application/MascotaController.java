package com.dbp.pet_journey.mascota.application;

import com.dbp.pet_journey.mascota.domain.MascotaService;
import com.dbp.pet_journey.mascota.dto.MascotaRequestDto;
import com.dbp.pet_journey.mascota.dto.MascotaResponseDto;
import com.dbp.pet_journey.mascota.dto.MascotaUpdateRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mascota")
public class MascotaController {
    @Autowired
    private MascotaService mascotaService;

    @GetMapping("/{id}")
    public ResponseEntity<MascotaResponseDto> getMascota(@PathVariable Long id){
        return ResponseEntity.ok(mascotaService.getMascota(id));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<MascotaResponseDto> updateMascota(
            @PathVariable Long id,
            @RequestBody MascotaUpdateRequestDto mascotaUpdateRequestDto) {

        return mascotaService.updateMascota(id, mascotaUpdateRequestDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMascota(@PathVariable Long id){
        mascotaService.deleteMascota(id);
        return ResponseEntity.noContent().build();
    }
}
