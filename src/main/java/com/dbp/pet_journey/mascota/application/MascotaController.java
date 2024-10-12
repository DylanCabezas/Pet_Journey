package com.dbp.pet_journey.mascota.application;


import com.dbp.pet_journey.mascota.domain.MascotaService;
import com.dbp.pet_journey.mascota.dto.MascotaRequestDto;
import com.dbp.pet_journey.mascota.dto.MascotaResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mascota")
public class MascotaController {
    @Autowired
    private MascotaService mascotaService;

    @PostMapping()
    public ResponseEntity<Void> saveMascota(@RequestBody MascotaRequestDto mascotaRequestDto) {
        mascotaService.saveMascota(mascotaRequestDto);
        return ResponseEntity.created(null).build();
    }

    @GetMapping("/id")
    public ResponseEntity<MascotaResponseDto> getMascota(@PathVariable Long id){
        return ResponseEntity.ok(mascotaService.getMascota(id));
    }

    @DeleteMapping("/id")
    public ResponseEntity<Void> deleteUsuario(@PathVariable Long id){
        mascotaService.deleteMascota(id);
        return ResponseEntity.noContent().build();
    }
}
