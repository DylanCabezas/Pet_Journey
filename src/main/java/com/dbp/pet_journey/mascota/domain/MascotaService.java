package com.dbp.pet_journey.mascota.domain;

import com.dbp.pet_journey.Exceptions.ResourceNotFoundException;
import com.dbp.pet_journey.mascota.dto.MascotaRequestDto;
import com.dbp.pet_journey.mascota.dto.MascotaResponseDto;
import com.dbp.pet_journey.mascota.dto.MascotaUpdateRequestDto;
import com.dbp.pet_journey.mascota.infraestructure.MascotaRepository;
import com.dbp.pet_journey.usuario.domain.Usuario;
import com.dbp.pet_journey.usuario.domain.UsuarioService;
import com.dbp.pet_journey.usuario.infraestructure.UsuarioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class MascotaService {
    @Autowired
    private MascotaRepository mascotaRepository;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private UsuarioRepository usuarioRepository;

    public Mascota saveMascota(MascotaRequestDto mascotaRequestDto, Usuario usuario) {
        Mascota mascota = new Mascota();
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.map(mascotaRequestDto, mascota);
        mascota.setUsuario(usuario);
        mascotaRepository.save(mascota);
        return mascota;
    }

    public MascotaResponseDto getMascota(Long id) {
        Mascota mascota = mascotaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(mascota, MascotaResponseDto.class);
    }

    public ResponseEntity<MascotaResponseDto> updateMascota(Long id, MascotaUpdateRequestDto mascotaUpdateRequestDto) {
        Mascota mascota = mascotaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Mascota no encontrada"));

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.map(mascotaUpdateRequestDto, mascota);

        mascotaRepository.save(mascota);

        MascotaResponseDto mascotaResponseDto = modelMapper.map(mascota, MascotaResponseDto.class);

        return ResponseEntity.ok(mascotaResponseDto);
    }
    

    public void deleteMascota(Long id) {
        mascotaRepository.deleteById(id);
    }

}
