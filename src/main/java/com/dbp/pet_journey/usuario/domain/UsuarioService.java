package com.dbp.pet_journey.usuario.domain;

import com.dbp.pet_journey.usuario.dto.UsuarioRequestDto;
import com.dbp.pet_journey.usuario.infraestructure.UsuarioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    private void saveUsuario(UsuarioRequestDto usuario) {
        Usuario usuarioEntity = new Usuario();
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.map(usuario, usuarioEntity);
        usuarioRepository.save(usuarioEntity);

    }
}
