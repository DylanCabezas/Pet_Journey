package com.dbp.pet_journey.usuario.application;

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
import com.dbp.pet_journey.usuario.dto.UsuarioRequestDto;
import com.dbp.pet_journey.usuario.dto.UsuarioResponseDto;
import com.dbp.pet_journey.usuario.dto.UsuarioUpdateRequestDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;


    @PostMapping("/register")
    public ResponseEntity<JwtAuthResponse> registerUsuario(@RequestBody @Valid UsuarioRequestDto usuarioRequestDto){
        return ResponseEntity.ok(usuarioService.register(usuarioRequestDto));
    }
    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> login(@RequestBody LoginReq req) {
        return ResponseEntity.ok(usuarioService.login(req));
    }

    @GetMapping("/{id}")
    public UsuarioResponseDto getUsuario(@PathVariable Long id){
        return usuarioService.getUsuario(id);
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
    public ResponseEntity<List<Mascota>> agregarMascota(@PathVariable Long usuarioId, @RequestBody @Valid MascotaRequestDto nuevaMascota) {
        List<Mascota> mascotas  = usuarioService.agregarMascota(usuarioId, nuevaMascota);
        return ResponseEntity.ok(mascotas);
    }


    @DeleteMapping("/{usuarioId}/eliminar_mascota/{mascotaId}")
    public ResponseEntity<Usuario> eliminarMascota(@PathVariable Long usuarioId, @PathVariable @Valid Long mascotaId) {
        Usuario usuarioActualizado = usuarioService.eliminarMascota(usuarioId, mascotaId);
        return ResponseEntity.ok(usuarioActualizado);
    }

    @PatchMapping("/actualizar_mascota/{mascotaId}")
    public ResponseEntity<MascotaUpdateResponseDto> actualizarMascota(@PathVariable Long mascotaId, @RequestBody @Valid MascotaUpdateRequestDto mascotaUpdateRequestDto) {
        return usuarioService.actualizarMascota(mascotaId,mascotaUpdateRequestDto);
    }

    @PutMapping("/AsignarServicio/{mascotaId}/{serivioId}")
    public ResponseEntity<ServicioResponseDto> setMascotaServicio(@PathVariable Long mascotaId, @RequestBody Long servicioId) {
        ServicioResponseDto servicio = usuarioService.setMascotaServicio(mascotaId,servicioId);
        return ResponseEntity.ok(servicio);
    }

}