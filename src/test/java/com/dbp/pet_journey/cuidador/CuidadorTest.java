package com.dbp.pet_journey.cuidador;

import com.dbp.pet_journey.cuidador.domain.Cuidador;
import com.dbp.pet_journey.cuidador.application.CuidadorController;
import com.dbp.pet_journey.cuidador.domain.CuidadorService;
import com.dbp.pet_journey.cuidador.dto.CuidadorRequestDto;
import com.dbp.pet_journey.cuidador.dto.CuidadorResponseDto;
import com.dbp.pet_journey.hospedaje.dto.HospedajeRequestDto;
import com.dbp.pet_journey.recomendacion.dto.RecomendacionDto;
import com.dbp.pet_journey.servicio.dto.ServicioRequestDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CuidadorTest {

    @Mock
    private CuidadorService cuidadorService;

    @InjectMocks
    private CuidadorController cuidadorController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveMascota_ShouldReturnCreatedStatus() {
        CuidadorRequestDto requestDto = new CuidadorRequestDto();
        doNothing().when(cuidadorService).saveCuidador(requestDto);

        ResponseEntity<Void> response = cuidadorController.saveMascota(requestDto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        verify(cuidadorService).saveCuidador(requestDto);
    }

    @Test
    void getCuidador_ShouldReturnCuidadorResponseDto() {
        Long cuidadorId = 1L;
        CuidadorResponseDto responseDto = new CuidadorResponseDto();
        when(cuidadorService.getCuidador(cuidadorId)).thenReturn(responseDto);

        ResponseEntity<CuidadorResponseDto> response = cuidadorController.getCuidador(cuidadorId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(responseDto, response.getBody());
        verify(cuidadorService).getCuidador(cuidadorId);
    }

    @Test
    void deleteCuidador_ShouldReturnNoContent() {
        Long cuidadorId = 1L;
        doNothing().when(cuidadorService).deleteCuidador(cuidadorId);

        ResponseEntity<Void> response = cuidadorController.deleteCuidador(cuidadorId);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(cuidadorService).deleteCuidador(cuidadorId);
    }

    @Test
    void agregarMascota_ShouldReturnUpdatedCuidador() {
        Long cuidadorId = 1L;
        ServicioRequestDto requestDto = new ServicioRequestDto();
        Cuidador updatedCuidador = new Cuidador();
        when(cuidadorService.crearServicio(cuidadorId, requestDto)).thenReturn(updatedCuidador);

        ResponseEntity<Cuidador> response = cuidadorController.agregarMascota(cuidadorId, requestDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedCuidador, response.getBody());
        verify(cuidadorService).crearServicio(cuidadorId, requestDto);
    }

    @Test
    void deleteServicio_ShouldReturnUpdatedCuidador() {
        Long cuidadorId = 1L;
        Long servicioId = 2L;
        Cuidador updatedCuidador = new Cuidador();
        when(cuidadorService.deleteServicio(cuidadorId, servicioId)).thenReturn(updatedCuidador);

        ResponseEntity<Cuidador> response = cuidadorController.deleteServicio(cuidadorId, servicioId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedCuidador, response.getBody());
        verify(cuidadorService).deleteServicio(cuidadorId, servicioId);
    }

    @Test
    void crearHospedaje_ShouldReturnUpdatedCuidador() {
        Long cuidadorId = 1L;
        HospedajeRequestDto requestDto = new HospedajeRequestDto();
        Cuidador updatedCuidador = new Cuidador();
        when(cuidadorService.crearHospedaje(cuidadorId, requestDto)).thenReturn(updatedCuidador);

        ResponseEntity<Cuidador> response = cuidadorController.crearhopedaje(cuidadorId, requestDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedCuidador, response.getBody());
        verify(cuidadorService).crearHospedaje(cuidadorId, requestDto);
    }

    @Test
    void agregarRecomendacion_ShouldReturnUpdatedCuidador() {
        Long cuidadorId = 1L;
        RecomendacionDto requestDto = new RecomendacionDto();
        Cuidador updatedCuidador = new Cuidador();
        when(cuidadorService.agregarRecomendacion(cuidadorId, requestDto)).thenReturn(updatedCuidador);

        ResponseEntity<Cuidador> response = cuidadorController.agregarRecomendacion(cuidadorId, requestDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedCuidador, response.getBody());
        verify(cuidadorService).agregarRecomendacion(cuidadorId, requestDto);
    }
}