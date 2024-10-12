package com.dbp.pet_journey.cuidador.application;

import com.dbp.pet_journey.cuidador.domain.CuidadorService;
import com.dbp.pet_journey.cuidador.dto.CuidadorRequestDto;
import com.dbp.pet_journey.cuidador.dto.CuidadorResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cuidador")
public class CuidadorController {
    @Autowired
    private CuidadorService cuidadorService;

    @PostMapping()
    public ResponseEntity<Void> saveMascota(@RequestBody CuidadorRequestDto cuidadorRequestDto) {
        cuidadorService.saveCuidador(cuidadorRequestDto);
        return ResponseEntity.created(null).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CuidadorResponseDto> getCuidador(@PathVariable Long id){
        return ResponseEntity.ok(cuidadorService.getCuidador(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable Long id){
        cuidadorService.deleteCuidador(id);
        return ResponseEntity.noContent().build();
    }
<<<<<<< HEAD


}
=======
}
>>>>>>> 7c8c9b326f7cc343bf0b419e1bcd32c6c96e4dfc
