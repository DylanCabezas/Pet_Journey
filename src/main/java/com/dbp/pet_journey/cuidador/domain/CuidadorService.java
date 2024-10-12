package com.dbp.pet_journey.cuidador.domain;

import com.dbp.pet_journey.Exceptions.ResourceNotFoundException;
import com.dbp.pet_journey.cuidador.dto.CuidadorRequestDto;
import com.dbp.pet_journey.cuidador.dto.CuidadorResponseDto;
import com.dbp.pet_journey.cuidador.infraestructure.CuidadorRepository;
import com.dbp.pet_journey.servicio.domain.Servicio;
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

    public void saveCuidador(CuidadorRequestDto cuidadorRequestDto) {
        Cuidador usuario = new Cuidador();
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.map(cuidadorRequestDto, usuario);
        cuidadorRepository.save(usuario);
        CuidadorResponseDto cuidadorResponseDto = modelMapper.map(usuario, CuidadorResponseDto.class);
        ResponseEntity.ok(cuidadorResponseDto);
    }

    public CuidadorResponseDto getCuidador(Long id) {
        Cuidador usuario = cuidadorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Cuidador no encontrado"));
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(usuario, CuidadorResponseDto.class);
    }

    public void crearServicio(Long id, ServicioRequestDto servicioRequestDto) {
        Cuidador cuidador = cuidadorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Cuidador no encontrado"));;
        Servicio servicio = new Servicio();
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.map(servicioRequestDto, servicio);
        servicioRepository.save(servicio);
        cuidador.getServicios().add(servicio);
        cuidadorRepository.save(cuidador);

    }

    public void deleteCuidador(Long id) {
        cuidadorRepository.deleteById(id);
    }
}
