package com.dbp.pet_journey.usuario.application;

import com.dbp.pet_journey.Exceptions.ResourceNotFoundException;
import com.dbp.pet_journey.auth.dto.JwtAuthResponse;
import com.dbp.pet_journey.auth.dto.LoginReq;
import com.dbp.pet_journey.mascota.domain.Mascota;
import com.dbp.pet_journey.mascota.dto.MascotaRequestDto;
import com.dbp.pet_journey.mascota.dto.MascotaResponseDto;
import com.dbp.pet_journey.mascota.dto.MascotaUpdateRequestDto;
import com.dbp.pet_journey.mascota.dto.MascotaUpdateResponseDto;
import com.dbp.pet_journey.servicio.dto.ServicioRequestDto;
import com.dbp.pet_journey.servicio.dto.ServicioResponseDto;
import com.dbp.pet_journey.usuario.domain.Usuario;
import com.dbp.pet_journey.usuario.domain.UsuarioService;
import com.dbp.pet_journey.usuario.dto.UsuarioLoginDto;
import com.dbp.pet_journey.usuario.dto.UsuarioRequestDto;
import com.dbp.pet_journey.usuario.dto.UsuarioResponseDto;
import com.dbp.pet_journey.usuario.dto.UsuarioUpdateRequestDto;
import com.dbp.pet_journey.usuario.infraestructure.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;

import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/{email}")
    public ResponseEntity<?> debugUser(@PathVariable String email) {
        Usuario usuario = usuarioRepository.findByEmail(email);
        if (usuario == null) {
            return ResponseEntity.status(404).body("Usuario no encontrado");
        }
        return ResponseEntity.ok(usuario);
    }

    @GetMapping("/")
    public ResponseEntity<String> hola(){
        return ResponseEntity.ok("Hola Mundo!");
    }

    @PostMapping("/register")
    public ResponseEntity<JwtAuthResponse> register(@RequestBody @Valid UsuarioRequestDto usuarioRequestDto){
        return ResponseEntity.ok(usuarioService.register(usuarioRequestDto));
    }
    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> login(@RequestBody @Valid UsuarioLoginDto req) {
        return ResponseEntity.ok(usuarioService.login(req));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getUsuario(@PathVariable Long id) {
            return ResponseEntity.ok(usuarioService.getUsuario(id));
    }
    @PatchMapping("/{id}")
    public ResponseEntity<UsuarioResponseDto> updateUsuario(
            @PathVariable Long id,
            @RequestBody @Valid UsuarioUpdateRequestDto usuarioUpdateRequestDto) {

        return usuarioService.updateUsuario(id, usuarioUpdateRequestDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable Long id){
        usuarioService.deleteUsuario(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{usuarioId}/agregar_mascota")
    public ResponseEntity<Page<Mascota>> agregarMascota(
            @PathVariable Long usuarioId,
            @RequestBody @Valid MascotaRequestDto nuevaMascota,
            @PageableDefault(size = 10, sort = "id") Pageable pageable) {
        Page<Mascota> mascotasPaginadas = usuarioService.agregarMascota(usuarioId, nuevaMascota, pageable);
        return ResponseEntity.ok(mascotasPaginadas);
    }


    @PatchMapping("/actualizar_mascota/{mascotaId}")
    public ResponseEntity<Page<MascotaResponseDto>> actualizarMascota(
            @PathVariable Long mascotaId,
            @RequestBody @Valid MascotaUpdateRequestDto mascotaUpdateRequestDto,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Page<MascotaResponseDto> mascotasPaginadas = usuarioService.actualizarMascota(mascotaId, mascotaUpdateRequestDto, page, size);
        return ResponseEntity.ok(mascotasPaginadas);
    }

    @PutMapping("/AsignarServicio/{mascotaId}/{serivioId}")
    public ResponseEntity<ServicioResponseDto> setMascotaServicio(@PathVariable Long mascotaId, @RequestBody Long servicioId) {
        ServicioResponseDto servicio = usuarioService.setMascotaServicio(mascotaId,servicioId);
        return ResponseEntity.ok(servicio);
    }
    @DeleteMapping("/{usuarioId}/eliminar_mascota/{mascotaId}")
    public ResponseEntity<Page<Mascota>> eliminarMascota(
            @PathVariable Long usuarioId,
            @PathVariable Long mascotaId,
            @PageableDefault(size = 10, sort = "id") Pageable pageable) {
        Page<Mascota> mascotasActualizadas = usuarioService.eliminarMascota(usuarioId, mascotaId, pageable);
        return ResponseEntity.ok(mascotasActualizadas);
    }

    @GetMapping("/{usuarioId}/mascota/{mascotaId}")
    public ResponseEntity<MascotaResponseDto> getMascota(
            @PathVariable Long usuarioId,
            @PathVariable Long mascotaId) {
        MascotaResponseDto mascota = usuarioService.getMascota(usuarioId, mascotaId);
        return ResponseEntity.ok(mascota);
    }

    @GetMapping("/{usuarioId}/mascotas")
    public ResponseEntity<Page<MascotaResponseDto>> getMascotas(
            @PathVariable Long usuarioId,
            @PageableDefault(size = 10, sort = "id") Pageable pageable) {
        Page<MascotaResponseDto> mascotas = usuarioService.getMascotas(usuarioId, pageable);
        return ResponseEntity.ok(mascotas);
    }

}