package com.dbp.pet_journey.cuidador.application;

import com.dbp.pet_journey.cuidador.domain.Cuidador;
import com.dbp.pet_journey.cuidador.domain.CuidadorService;
import com.dbp.pet_journey.cuidador.dto.CuidadorRequestDto;
import com.dbp.pet_journey.cuidador.dto.CuidadorResponseDto;
import com.dbp.pet_journey.mascota.dto.MascotaRequestDto;
import com.dbp.pet_journey.servicio.dto.ServicioRequestDto;
import com.dbp.pet_journey.usuario.domain.Usuario;
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
    public ResponseEntity<Void> deleteCuidador(@PathVariable Long id){
        cuidadorService.deleteCuidador(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{usuarioId}/agregar_servicio")
    public ResponseEntity<Cuidador> agregarMascota(@PathVariable Long usuarioId, @RequestBody ServicioRequestDto servicioRequestDto) {
        Cuidador CuidadorActualizado = cuidadorService.crearServicio(usuarioId, servicioRequestDto);
        return ResponseEntity.ok(CuidadorActualizado);
    }



}
