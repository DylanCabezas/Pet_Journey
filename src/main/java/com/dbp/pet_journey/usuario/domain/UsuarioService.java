package com.dbp.pet_journey.usuario.domain;

import com.dbp.pet_journey.Exceptions.ResourceNotFoundException;
import com.dbp.pet_journey.usuario.dto.UsuarioRequestDto;
import com.dbp.pet_journey.usuario.dto.UsuarioResponseDto;
import com.dbp.pet_journey.usuario.infraestructure.UsuarioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public void loginUsuario(UsuarioRequestDto usuarioRequestDto) {
        Usuario usuario = new Usuario();
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.map(usuarioRequestDto, usuario);
        usuarioRepository.save(usuario);
        UsuarioResponseDto usuarioResponseDto = modelMapper.map(usuario, UsuarioResponseDto.class);
        ResponseEntity.ok(usuarioResponseDto);
    }

    public UsuarioResponseDto getUsuario(Long id) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(usuario, UsuarioResponseDto.class);
    }

    public void deleteUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }

    public void UpdateUsuario(Long id, UsuarioRequestDto usuarioRequestDto) {

    }

}
