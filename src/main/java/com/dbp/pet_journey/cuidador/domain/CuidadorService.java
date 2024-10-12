package com.dbp.pet_journey.cuidador.domain;

import com.dbp.pet_journey.Exceptions.ResourceNotFoundException;
import com.dbp.pet_journey.cuidador.dto.CuidadorRequestDto;
import com.dbp.pet_journey.cuidador.dto.CuidadorResponseDto;
import com.dbp.pet_journey.cuidador.infraestructure.CuidadorRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CuidadorService {
    @Autowired
    private CuidadorRepository cuidadorRepository;

    public void saveCuidador(CuidadorRequestDto cuidadorRequestDto) {
        Cuidador usuario = new Cuidador();
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.map(cuidadorRequestDto, usuario);
        cuidadorRepository.save(usuario);
        CuidadorResponseDto cuidadorResponseDto = modelMapper.map(usuario, CuidadorResponseDto.class);
        ResponseEntity.ok(cuidadorResponseDto);
    }

    public CuidadorResponseDto getCuidador(Long id) {
        Cuidador usuario = cuidadorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(usuario, CuidadorResponseDto.class);
    }

    public void deleteCuidador(Long id) {
        cuidadorRepository.deleteById(id);
    }
}
