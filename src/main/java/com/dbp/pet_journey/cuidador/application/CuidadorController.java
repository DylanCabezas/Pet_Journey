package com.dbp.pet_journey.cuidador.application;

import com.dbp.pet_journey.auth.dto.JwtAuthResponse;
import com.dbp.pet_journey.auth.dto.LoginReq;
import com.dbp.pet_journey.cuidador.domain.Cuidador;
import com.dbp.pet_journey.cuidador.domain.CuidadorService;
import com.dbp.pet_journey.cuidador.dto.CuidadorLoginDto;
import com.dbp.pet_journey.cuidador.dto.CuidadorRequestDto;
import com.dbp.pet_journey.cuidador.dto.CuidadorResponseDto;
import com.dbp.pet_journey.hospedaje.domain.Hospedaje;
import com.dbp.pet_journey.hospedaje.dto.HospedajeRequestDto;
import com.dbp.pet_journey.hospedaje.dto.HospedajeResponseDto;
import com.dbp.pet_journey.mascota.dto.MascotaRequestDto;
import com.dbp.pet_journey.recomendacion.domain.Recomendacion;
import com.dbp.pet_journey.recomendacion.dto.RecomendacionDto;
import com.dbp.pet_journey.servicio.domain.Servicio;
import com.dbp.pet_journey.servicio.dto.ServicioRequestDto;
import com.dbp.pet_journey.usuario.domain.Usuario;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cuidador")
public class CuidadorController {
    @Autowired
    private CuidadorService cuidadorService;

    @PostMapping("/register")
    public ResponseEntity<JwtAuthResponse> register(@RequestBody CuidadorRequestDto cuidadorRequestDto) {
        return ResponseEntity.ok(cuidadorService.register(cuidadorRequestDto));
    }

    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> login(@RequestBody CuidadorLoginDto req) {
        return ResponseEntity.ok(cuidadorService.login(req));
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
    public ResponseEntity<Page<Servicio>> agregarMascota(
            @PathVariable Long cuidadorId,
            @RequestBody @Valid ServicioRequestDto servicioRequestDto,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        cuidadorService.crearServicio(cuidadorId, servicioRequestDto);
        Page<Servicio> serviciosPaginados = cuidadorService.getServiciosPaginados(cuidadorId, page, size);
        return ResponseEntity.ok(serviciosPaginados);
    }

    @DeleteMapping("/{cuidadorId}/eliminar_servicio/{servicioId}")
    public ResponseEntity<Page<Servicio>> deleteServicio(
            @PathVariable Long cuidadorId,
            @PathVariable Long servicioId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        cuidadorService.deleteServicio(cuidadorId, servicioId);
        Page<Servicio> serviciosPaginados = cuidadorService.getServiciosPaginados(cuidadorId, page, size);
        return ResponseEntity.ok(serviciosPaginados);
    }

    @PutMapping("/{cuidadorId}/agregar_hospedaje")
    public ResponseEntity<HospedajeResponseDto> crearhopedaje(
            @PathVariable Long cuidadorId,
            @RequestBody @Valid HospedajeRequestDto hospedajeRequestDto) {

        Hospedaje hospedaje = cuidadorService.crearHospedaje(cuidadorId, hospedajeRequestDto);
        ModelMapper modelMapper = new ModelMapper();
        HospedajeResponseDto hospedajeResponseDto = modelMapper.map(hospedaje, HospedajeResponseDto.class);
        return ResponseEntity.ok(hospedajeResponseDto);
    }

    @PutMapping("{cuidadorId}/agregar_recomendacion")
    public ResponseEntity<Page<RecomendacionDto>> agregarRecomendacion(
            @PathVariable Long cuidadorId,
            @RequestBody @Valid RecomendacionDto recomendacionDto,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        cuidadorService.agregarRecomendacion(cuidadorId, recomendacionDto);
        Page<Recomendacion> recomendacionesPaginadas = cuidadorService.getRecomendacionesPaginadas(cuidadorId, page, size);
        ModelMapper modelMapper = new ModelMapper();
        Page<RecomendacionDto> recomendacionesDtoPaginadas = recomendacionesPaginadas.map(recomendacion -> modelMapper.map(recomendacion, RecomendacionDto.class));
        return ResponseEntity.ok(recomendacionesDtoPaginadas);
    }



}
