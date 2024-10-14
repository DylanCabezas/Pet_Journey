package com.dbp.pet_journey.Usuario;

import com.dbp.pet_journey.mascota.domain.Mascota;
import com.dbp.pet_journey.mascota.dto.MascotaRequestDto;
import com.dbp.pet_journey.mascota.dto.MascotaUpdateRequestDto;
import com.dbp.pet_journey.mascota.dto.MascotaUpdateResponseDto;
import com.dbp.pet_journey.servicio.dto.ServicioResponseDto;
import com.dbp.pet_journey.usuario.application.UsuarioController;
import com.dbp.pet_journey.usuario.domain.Usuario;
import com.dbp.pet_journey.usuario.domain.UsuarioService;
import com.dbp.pet_journey.usuario.dto.UsuarioRequestDto;
import com.dbp.pet_journey.usuario.dto.UsuarioResponseDto;
import com.dbp.pet_journey.usuario.dto.UsuarioUpdateRequestDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UsuarioTest {

    @Mock
    private UsuarioService usuarioService;

    @InjectMocks
    private UsuarioController usuarioController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void loginUsuario_ShouldReturnCreatedStatus() {
        UsuarioRequestDto requestDto = new UsuarioRequestDto();
        when(usuarioService.loginUsuario(requestDto)).thenReturn(ResponseEntity.ok().build());

        ResponseEntity<Void> response = usuarioController.loginUsuario(requestDto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        verify(usuarioService).loginUsuario(requestDto);
    }

    @Test
    void getUsuario_ShouldReturnUsuarioResponseDto() {
        Long userId = 1L;
        UsuarioResponseDto responseDto = new UsuarioResponseDto();
        when(usuarioService.getUsuario(userId)).thenReturn(responseDto);

        ResponseEntity<UsuarioResponseDto> response = usuarioController.getUsuario(userId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(responseDto, response.getBody());
        verify(usuarioService).getUsuario(userId);
    }

    @Test
    void updateUsuario_ShouldReturnUpdatedUsuarioResponseDto() {
        Long userId = 1L;
        UsuarioUpdateRequestDto requestDto = new UsuarioUpdateRequestDto();
        UsuarioResponseDto responseDto = new UsuarioResponseDto();
        when(usuarioService.updateUsuario(userId, requestDto)).thenReturn(ResponseEntity.ok(responseDto));

        ResponseEntity<UsuarioResponseDto> response = usuarioController.updateUsuario(userId, requestDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(responseDto, response.getBody());
        verify(usuarioService).updateUsuario(userId, requestDto);
    }

    @Test
    void deleteUsuario_ShouldReturnNoContent() {
        Long userId = 1L;
        doNothing().when(usuarioService).deleteUsuario(userId);

        ResponseEntity<Void> response = usuarioController.deleteUsuario(userId);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(usuarioService).deleteUsuario(userId);
    }

    @Test
    void agregarMascota_ShouldReturnListOfMascotas() {
        Long userId = 1L;
        MascotaRequestDto requestDto = new MascotaRequestDto();
        List<Mascota> mascotas = Arrays.asList(new Mascota(), new Mascota());
        when(usuarioService.agregarMascota(userId, requestDto)).thenReturn(mascotas);

        ResponseEntity<List<Mascota>> response = usuarioController.agregarMascota(userId, requestDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mascotas, response.getBody());
        verify(usuarioService).agregarMascota(userId, requestDto);
    }

    @Test
    void eliminarMascota_ShouldReturnUpdatedUsuario() {
        Long userId = 1L;
        Long mascotaId = 2L;
        Usuario updatedUsuario = new Usuario();
        when(usuarioService.eliminarMascota(userId, mascotaId)).thenReturn(updatedUsuario);

        ResponseEntity<Usuario> response = usuarioController.eliminarMascota(userId, mascotaId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedUsuario, response.getBody());
        verify(usuarioService).eliminarMascota(userId, mascotaId);
    }

    @Test
    void actualizarMascota_ShouldReturnMascotaUpdateResponseDto() {
        Long mascotaId = 1L;
        MascotaUpdateRequestDto requestDto = new MascotaUpdateRequestDto();
        MascotaUpdateResponseDto responseDto = new MascotaUpdateResponseDto();
        when(usuarioService.actualizarMascota(mascotaId, requestDto)).thenReturn(ResponseEntity.ok(responseDto));

        ResponseEntity<MascotaUpdateResponseDto> response = usuarioController.actualizarMascota(mascotaId, requestDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(responseDto, response.getBody());
        verify(usuarioService).actualizarMascota(mascotaId, requestDto);
    }

    @Test
    void setMascotaServicio_ShouldReturnServicioResponseDto() {
        Long mascotaId = 1L;
        Long servicioId = 2L;
        ServicioResponseDto responseDto = new ServicioResponseDto();
        when(usuarioService.setMascotaServicio(mascotaId, servicioId)).thenReturn(responseDto);

        ResponseEntity<ServicioResponseDto> response = usuarioController.setMascotaServicio(mascotaId, servicioId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(responseDto, response.getBody());
        verify(usuarioService).setMascotaServicio(mascotaId, servicioId);
    }
}
