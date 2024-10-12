package com.dbp.pet_journey.usuario.application;

import com.dbp.pet_journey.mascota.dto.MascotaRequestDto;
import com.dbp.pet_journey.usuario.domain.Usuario;
import com.dbp.pet_journey.usuario.domain.UsuarioService;
import com.dbp.pet_journey.usuario.dto.UsuarioRequestDto;
import com.dbp.pet_journey.usuario.dto.UsuarioResponseDto;
import com.dbp.pet_journey.usuario.dto.UsuarioUpdateRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @PostMapping()
    public ResponseEntity<Void> loginUsuario(@RequestBody UsuarioRequestDto usuarioRequestDto){
       usuarioService.loginUsuario(usuarioRequestDto);
       return ResponseEntity.created(null).build();
    }

    @GetMapping("/id")
    public ResponseEntity<UsuarioResponseDto> getUsuario(@PathVariable Long id){
        return ResponseEntity.ok(usuarioService.getUsuario(id));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UsuarioResponseDto> updateUsuario(
            @PathVariable Long id,
            @RequestBody UsuarioUpdateRequestDto usuarioUpdateRequestDto) {

        return usuarioService.updateUsuario(id, usuarioUpdateRequestDto);
    }

    @DeleteMapping("/id")
    public ResponseEntity<Void> deleteUsuario(@PathVariable Long id){
        usuarioService.deleteUsuario(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{usuarioId}/agregar_mascota")
    public ResponseEntity<Usuario> agregarMascota(@PathVariable Long usuarioId, @RequestBody MascotaRequestDto nuevaMascota) {
        Usuario usuarioActualizado = usuarioService.agregarMascota(usuarioId, nuevaMascota);
        return ResponseEntity.ok(usuarioActualizado);
    }

}
