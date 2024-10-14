package com.dbp.pet_journey.cuidador.application;

import com.dbp.pet_journey.cuidador.domain.Cuidador;
import com.dbp.pet_journey.cuidador.domain.CuidadorService;
import com.dbp.pet_journey.cuidador.dto.CuidadorRequestDto;
import com.dbp.pet_journey.cuidador.dto.CuidadorResponseDto;
import com.dbp.pet_journey.hospedaje.dto.HospedajeRequestDto;
import com.dbp.pet_journey.mascota.dto.MascotaRequestDto;
import com.dbp.pet_journey.recomendacion.dto.RecomendacionDto;
import com.dbp.pet_journey.servicio.dto.ServicioRequestDto;
import com.dbp.pet_journey.usuario.domain.Usuario;
import jakarta.validation.Valid;
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

    @PutMapping("/{cuidadorId}/agregar_servicio")
    public ResponseEntity<Cuidador> agregarMascota(@PathVariable Long cuidadorId, @RequestBody @Valid ServicioRequestDto servicioRequestDto) {
        Cuidador CuidadorActualizado = cuidadorService.crearServicio(cuidadorId, servicioRequestDto);
        return ResponseEntity.ok(CuidadorActualizado);
    }

    @DeleteMapping("/{cuidadorId}/eliminar_servicio/{servicioId}")
    public ResponseEntity<Cuidador>  deleteServicio(@PathVariable Long cuidadorId, @PathVariable Long servicioId) {
        Cuidador cuidadorActualizado = cuidadorService.deleteServicio(cuidadorId, servicioId);
        return ResponseEntity.ok(cuidadorActualizado);
    }

    @PutMapping("/{cuidadorId}/agregar_hospedaje")
    public ResponseEntity<Cuidador> crearhopedaje(@PathVariable Long cuidadorId, @RequestBody @Valid HospedajeRequestDto hospedajeRequestDto) {
        Cuidador CuidadorActualizado = cuidadorService.crearHospedaje(cuidadorId, hospedajeRequestDto);
        return ResponseEntity.ok(CuidadorActualizado);
    }

    @PutMapping("{cuidadorId}/agregar_recomendacion")
    public ResponseEntity<Cuidador>  agregarRecomendacion(@PathVariable Long cuidadorId, @RequestBody @Valid RecomendacionDto recomendacionDto) {
        Cuidador cuidadorActualizado = cuidadorService.agregarRecomendacion(cuidadorId,recomendacionDto);
        return ResponseEntity.ok(cuidadorActualizado);
    }



}
