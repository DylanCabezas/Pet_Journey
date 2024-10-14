package com.dbp.pet_journey.usuario.application;

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
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @PostMapping()
    public ResponseEntity<Void> loginUsuario(@RequestBody @Valid UsuarioRequestDto usuarioRequestDto){
        usuarioService.loginUsuario(usuarioRequestDto);
        return ResponseEntity.created(null).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDto> getUsuario(@PathVariable Long id){
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