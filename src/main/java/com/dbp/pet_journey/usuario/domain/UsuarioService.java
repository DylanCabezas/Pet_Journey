package com.dbp.pet_journey.usuario.domain;

import com.dbp.pet_journey.Exceptions.ResourceNotFoundException;
import com.dbp.pet_journey.mascota.domain.Mascota;
import com.dbp.pet_journey.mascota.domain.MascotaService;
import com.dbp.pet_journey.mascota.dto.MascotaRequestDto;
import com.dbp.pet_journey.mascota.dto.MascotaUpdateRequestDto;
import com.dbp.pet_journey.mascota.dto.MascotaUpdateResponseDto;
import com.dbp.pet_journey.mascota.infraestructure.MascotaRepository;
import com.dbp.pet_journey.servicio.domain.EstadoServicio;
import com.dbp.pet_journey.servicio.domain.Servicio;
import com.dbp.pet_journey.servicio.dto.ServicioRequestDto;
import com.dbp.pet_journey.servicio.dto.ServicioResponseDto;
import com.dbp.pet_journey.servicio.infraestructure.ServicioRepository;
import com.dbp.pet_journey.usuario.dto.UsuarioRequestDto;
import com.dbp.pet_journey.usuario.dto.UsuarioResponseDto;
import com.dbp.pet_journey.usuario.dto.UsuarioUpdateRequestDto;
import com.dbp.pet_journey.usuario.infraestructure.UsuarioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private MascotaService mascotaService;
    @Autowired
    private MascotaRepository mascotaRepository;
    @Autowired
    private ServicioRepository servicioRepository;

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


    public ResponseEntity<UsuarioResponseDto> updateUsuario(Long id, UsuarioUpdateRequestDto usuarioUpdateRequestDto) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));

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


    public Usuario eliminarMascota(Long usuarioId, Long mascotaId) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));

        Mascota mascota = mascotaRepository.findById(mascotaId)
                .orElseThrow(() -> new ResourceNotFoundException("Mascota no encontrada"));

        usuario.getMascotas().remove(mascota);

        mascotaRepository.delete(mascota);

        return usuarioRepository.save(usuario);
    }

    public ResponseEntity<MascotaUpdateResponseDto> actualizarMascota( Long mascotaId, MascotaUpdateRequestDto mascotaUpdateRequestDto){
        return  mascotaService.updateMascota(mascotaId,mascotaUpdateRequestDto);
    }

    public ServicioResponseDto setMascotaServicio(Long mascotaId, Long servicioId) {
        Servicio servicio = servicioRepository.findById(servicioId).orElseThrow(() -> new ResourceNotFoundException("Servicio no encontrado"));
        Mascota mascota = mascotaRepository.findById(mascotaId).orElseThrow(() -> new ResourceNotFoundException("Mascota no encontrada"));
        servicio.getMascotas().add(mascota);
        mascota.setCuidador(servicio.getCuidador());
        servicio.setEstado(EstadoServicio.PENDIENTE);
        servicioRepository.save(servicio);
        ServicioResponseDto servicioResponseDto = new ServicioResponseDto();
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.map(servicio, ServicioResponseDto.class);
        return servicioResponseDto;
    }


}
