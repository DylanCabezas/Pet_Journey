package com.dbp.pet_journey.mascota.domain;

import com.dbp.pet_journey.Exceptions.ResourceNotFoundException;
import com.dbp.pet_journey.mascota.dto.MascotaRequestDto;
import com.dbp.pet_journey.mascota.dto.MascotaResponseDto;
import com.dbp.pet_journey.mascota.infraestructure.MascotaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class MascotaService {
    @Autowired
    private MascotaRepository mascotaRepository;

    public void saveMascota(MascotaRequestDto mascotaRequestDto) {
        Mascota mascota = new Mascota();
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.map(mascotaRequestDto, mascota);
        mascotaRepository.save(mascota);
        MascotaResponseDto mascotaResponseDto = modelMapper.map(mascota, MascotaResponseDto.class);
        ResponseEntity.ok(mascotaResponseDto);
    }

    public MascotaResponseDto getMascota(Long id) {
        Mascota mascota = mascotaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(mascota, MascotaResponseDto.class);
    }

    public void deleteMascota(Long id) {
        mascotaRepository.deleteById(id);
    }

}
