package com.dbp.pet_journey.usuario.domain;

import com.dbp.pet_journey.Exceptions.ResourceConflictException;
import com.dbp.pet_journey.Exceptions.ResourceNotFoundException;
import com.dbp.pet_journey.mascota.domain.Mascota;
import com.dbp.pet_journey.mascota.dto.MascotaRequestDto;
import com.dbp.pet_journey.mascota.dto.MascotaUpdateRequestDto;
import com.dbp.pet_journey.mascota.dto.MascotaUpdateResponseDto;
import com.dbp.pet_journey.mascota.infraestructure.MascotaRepository;
import com.dbp.pet_journey.servicio.domain.Servicio;
import com.dbp.pet_journey.servicio.dto.ServicioResponseDto;
import com.dbp.pet_journey.servicio.infraestructure.ServicioRepository;
import com.dbp.pet_journey.usuario.dto.UsuarioRequestDto;
import com.dbp.pet_journey.usuario.dto.UsuarioResponseDto;
import com.dbp.pet_journey.usuario.dto.UsuarioUpdateRequestDto;
import com.dbp.pet_journey.usuario.infraestructure.UsuarioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private UsuarioRepository usuarioRepository;
    private MascotaRepository mascotaRepository;
    private ServicioRepository servicioRepository;
    private ModelMapper modelMapper;

    @Value("${MAIL_USERNAME}")
    String MAIL_USERNAME;

    public UsuarioService(UsuarioRepository usuarioRepository, MascotaRepository mascotaRepository, ServicioRepository servicioRepository, ModelMapper modelMapper) {
        this.usuarioRepository = usuarioRepository;
        this.mascotaRepository = mascotaRepository;
        this.servicioRepository = servicioRepository;
        this.modelMapper = modelMapper;
    }

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
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));
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

        modelMapper.map(usuarioUpdateRequestDto, usuario);
        usuarioRepository.save(usuario);

        UsuarioResponseDto usuarioResponseDto = modelMapper.map(usuario, UsuarioResponseDto.class);
        return ResponseEntity.ok(usuarioResponseDto);
    }

    public Usuario agregarMascota(Long id, MascotaRequestDto mascotaRequestDto) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));

        Mascota mascota = new Mascota();
        modelMapper.map(mascotaRequestDto, mascota);
        mascota.setUsuario(usuario);
        mascotaRepository.save(mascota);

        usuario.getMascotas().add(mascota);
        usuarioRepository.save(usuario);
        return usuario;
    }

    public Usuario eliminarMascota(Long usuarioId, Long mascotaId) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));

        Mascota mascota = mascotaRepository.findById(mascotaId)
                .orElseThrow(() -> new ResourceNotFoundException("Mascota no encontrada"));

        usuario.getMascotas().remove(mascota);
        mascotaRepository.delete(mascota);

        return usuarioRepository.save(usuario);
    }

    public ResponseEntity<MascotaUpdateResponseDto> actualizarMascota(Long mascotaId, MascotaUpdateRequestDto mascotaUpdateRequestDto) {
        Mascota mascota = mascotaRepository.findById(mascotaId)
                .orElseThrow(() -> new ResourceNotFoundException("Mascota no encontrada"));

        modelMapper.map(mascotaUpdateRequestDto, mascota);
        mascotaRepository.save(mascota);

        MascotaUpdateResponseDto mascotaUpdateResponseDto = modelMapper.map(mascota, MascotaUpdateResponseDto.class);
        return ResponseEntity.ok(mascotaUpdateResponseDto);
    }

    public ServicioResponseDto setMascotaServicio(Long mascotaId, Long servicioId) {
        Servicio servicio = servicioRepository.findById(servicioId)
                .orElseThrow(() -> new ResourceNotFoundException("Servicio no encontrado"));

        Mascota mascota = mascotaRepository.findById(mascotaId)
                .orElseThrow(() -> new ResourceNotFoundException("Mascota no encontrada"));

        servicio.getMascotas().add(mascota);
        mascota.setCuidador(servicio.getCuidador());
        servicioRepository.save(servicio);

        return modelMapper.map(servicio, ServicioResponseDto.class);
    }
}
