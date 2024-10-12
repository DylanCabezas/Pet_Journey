package com.dbp.pet_journey.usuario.domain;

import com.dbp.pet_journey.Exceptions.ResourceConflictException;
import com.dbp.pet_journey.Exceptions.ResourceNotFoundException;
import com.dbp.pet_journey.mascota.domain.Mascota;
import com.dbp.pet_journey.mascota.domain.MascotaService;
import com.dbp.pet_journey.mascota.dto.MascotaRequestDto;
import com.dbp.pet_journey.usuario.dto.UsuarioRequestDto;
import com.dbp.pet_journey.usuario.dto.UsuarioResponseDto;
import com.dbp.pet_journey.usuario.dto.UsuarioUpdateRequestDto;
import com.dbp.pet_journey.usuario.infraestructure.UsuarioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private MascotaService mascotaService;

    @Autowired
    private ModelMapper modelMapper;

    public ResponseEntity<UsuarioResponseDto> loginUsuario(UsuarioRequestDto usuarioRequestDto) {
        if (usuarioRepository.existsByUsername(usuarioRequestDto.getUsername())) {
            throw new ResourceConflictException("El nombre de usuario ya está en uso");
        }

        if (usuarioRepository.existsByEmail(usuarioRequestDto.getEmail())) {
            throw new ResourceConflictException("El correo electrónico ya está registrado");
        }

        Usuario usuario = new Usuario();
        modelMapper.map(usuarioRequestDto, usuario);
        usuarioRepository.save(usuario);
        UsuarioResponseDto usuarioResponseDto = modelMapper.map(usuario, UsuarioResponseDto.class);
        return ResponseEntity.ok(usuarioResponseDto);
    }

    public UsuarioResponseDto getUsuario(Long id) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(usuario, UsuarioResponseDto.class);
    }

    public void deleteUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }

    public ResponseEntity<UsuarioResponseDto> updateUsuario(Long id, UsuarioUpdateRequestDto usuarioUpdateRequestDto) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));

        if (!usuario.getUsername().equals(usuarioUpdateRequestDto.getUsername()) &&
                usuarioRepository.existsByUsername(usuarioUpdateRequestDto.getUsername())) {
            throw new ResourceConflictException("El nombre de usuario ya está en uso");
        }

        if (!usuario.getEmail().equals(usuarioUpdateRequestDto.getEmail()) &&
                usuarioRepository.existsByEmail(usuarioUpdateRequestDto.getEmail())) {
            throw new ResourceConflictException("El correo electrónico ya está registrado");
        }

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.map(usuarioUpdateRequestDto, usuario);

        usuarioRepository.save(usuario);

        UsuarioResponseDto usuarioResponseDto = modelMapper.map(usuario, UsuarioResponseDto.class);

        return ResponseEntity.ok(usuarioResponseDto);
    }

    public Usuario agregarMascota(Long id, MascotaRequestDto mascotaRequestDto) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));
        Mascota mascota= mascotaService.saveMascota(mascotaRequestDto,usuario);
        usuario.getMascotas().add(mascota);
        usuarioRepository.save(usuario);
        return usuario;
    }


}
