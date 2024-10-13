package com.dbp.pet_journey.cuidador.domain;

import com.dbp.pet_journey.Exceptions.ResourceNotFoundException;
import com.dbp.pet_journey.cuidador.dto.CuidadorRequestDto;
import com.dbp.pet_journey.cuidador.dto.CuidadorResponseDto;
import com.dbp.pet_journey.cuidador.infraestructure.CuidadorRepository;
import com.dbp.pet_journey.hospedaje.domain.Hospedaje;
import com.dbp.pet_journey.hospedaje.domain.HospedajeService;
import com.dbp.pet_journey.hospedaje.dto.HospedajeRequestDto;
import com.dbp.pet_journey.servicio.domain.Servicio;
import com.dbp.pet_journey.servicio.domain.ServicioService;
import com.dbp.pet_journey.servicio.dto.ServicioRequestDto;
import com.dbp.pet_journey.servicio.infraestructure.ServicioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CuidadorService {
    @Autowired
    private CuidadorRepository cuidadorRepository;
    @Autowired
    private ServicioRepository servicioRepository;
    @Autowired
    private ServicioService servicioService;
    @Autowired
    private HospedajeService hospedajeService;

    public void saveCuidador(CuidadorRequestDto cuidadorRequestDto) {
        Cuidador usuario = new Cuidador();
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.map(cuidadorRequestDto, usuario);
        cuidadorRepository.save(usuario);
        CuidadorResponseDto cuidadorResponseDto = modelMapper.map(usuario, CuidadorResponseDto.class);
        ResponseEntity.ok(cuidadorResponseDto);
    }

    public CuidadorResponseDto getCuidador(Long id) {
        Cuidador cuidador = cuidadorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Cuidador no encontrado"));
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(cuidador, CuidadorResponseDto.class);
    }

    public Cuidador crearServicio(Long cuidadorId, ServicioRequestDto servicioRequestDto) {
        Cuidador cuidador = cuidadorRepository.findById(cuidadorId).orElseThrow(() -> new ResourceNotFoundException("Cuidador no encontrado"));
        Servicio servicio = servicioService.postServicio(servicioRequestDto, cuidador);
        cuidador.getServicios().add(servicio);
        cuidadorRepository.save(cuidador);
        return  cuidador;
    }

    public Cuidador crearHospedaje(Long cuidadorId, HospedajeRequestDto hospedajeRequestDto) {
        Cuidador cuidador = cuidadorRepository.findById(cuidadorId).orElseThrow(() -> new ResourceNotFoundException("Cuidador no encontrado"));
        Hospedaje hospedaje = hospedajeService.crearHopedaje(hospedajeRequestDto, cuidadorId);
        cuidador.setHospedaje(hospedaje);
        cuidadorRepository.save(cuidador);
        return  cuidador;
    }

    public Cuidador deleteServicio(Long cuidadorId, Long servicioId){
        Cuidador cuidador = cuidadorRepository.findById(cuidadorId)
                .orElseThrow(() -> new ResourceNotFoundException("Cuidador no encontrado"));
        Servicio servicio = servicioRepository.findById(servicioId)
                .orElseThrow(() -> new ResourceNotFoundException("Servicio no encontrado"));
        cuidador.getServicios().remove(servicio);
        servicioRepository.delete(servicio);
        return cuidadorRepository.save(cuidador);
    }

    public void deleteCuidador(Long id) {
        cuidadorRepository.deleteById(id);
    }
}
