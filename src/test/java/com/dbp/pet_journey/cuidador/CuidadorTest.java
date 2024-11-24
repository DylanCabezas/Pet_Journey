package com.dbp.pet_journey.cuidador;

import com.dbp.pet_journey.auth.dto.JwtAuthResponse;
import com.dbp.pet_journey.cuidador.application.CuidadorController;
import com.dbp.pet_journey.cuidador.domain.CuidadorService;
import com.dbp.pet_journey.cuidador.dto.CuidadorLoginDto;
import com.dbp.pet_journey.cuidador.dto.CuidadorRequestDto;
import com.dbp.pet_journey.cuidador.dto.CuidadorResponseDto;
import com.dbp.pet_journey.hospedaje.domain.Hospedaje;
import com.dbp.pet_journey.hospedaje.dto.HospedajeRequestDto;
import com.dbp.pet_journey.hospedaje.dto.HospedajeResponseDto;
import com.dbp.pet_journey.recomendacion.domain.Recomendacion;
import com.dbp.pet_journey.recomendacion.dto.RecomendacionDto;
import com.dbp.pet_journey.servicio.domain.Servicio;
import com.dbp.pet_journey.servicio.dto.ServicioRequestDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class CuidadorControllerTest {

    @Mock
    private CuidadorService cuidadorService;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private CuidadorController cuidadorController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRegister() {
        CuidadorRequestDto requestDto = new CuidadorRequestDto();
        JwtAuthResponse expectedResponse = new JwtAuthResponse();
        when(cuidadorService.register(requestDto)).thenReturn(expectedResponse);

        ResponseEntity<JwtAuthResponse> response = cuidadorController.register(requestDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedResponse, response.getBody());
    }

    @Test
    void testLogin() {
        CuidadorLoginDto loginDto = new CuidadorLoginDto();
        JwtAuthResponse expectedResponse = new JwtAuthResponse();
        when(cuidadorService.login(loginDto)).thenReturn(expectedResponse);

        ResponseEntity<JwtAuthResponse> response = cuidadorController.login(loginDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedResponse, response.getBody());
    }

    @Test
    void testGetCuidador() {
        Long id = 1L;
        CuidadorResponseDto expectedResponse = new CuidadorResponseDto();
        when(cuidadorService.getCuidador(id)).thenReturn(expectedResponse);

        ResponseEntity<CuidadorResponseDto> response = cuidadorController.getCuidador(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedResponse, response.getBody());
    }

    @Test
    void testDeleteCuidador() {
        Long id = 1L;

        ResponseEntity<Void> response = cuidadorController.deleteCuidador(id);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(cuidadorService).deleteCuidador(id);
    }

    @Test
    void testAgregarServicio() {
        Long cuidadorId = 1L;
        ServicioRequestDto requestDto = new ServicioRequestDto();
        Page<Servicio> expectedPage = new PageImpl<>(Arrays.asList(new Servicio()));
        when(cuidadorService.getServiciosPaginados(cuidadorId, 0, 10)).thenReturn(expectedPage);

        ResponseEntity<Page<Servicio>> response = cuidadorController.agregarMascota(cuidadorId, requestDto, 0, 10);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedPage, response.getBody());
        verify(cuidadorService).crearServicio(cuidadorId, requestDto);
    }

    @Test
    void testDeleteServicio() {
        Long cuidadorId = 1L;
        Long servicioId = 2L;
        Page<Servicio> expectedPage = new PageImpl<>(Arrays.asList(new Servicio()));
        when(cuidadorService.getServiciosPaginados(cuidadorId, 0, 10)).thenReturn(expectedPage);

        ResponseEntity<Page<Servicio>> response = cuidadorController.deleteServicio(cuidadorId, servicioId, 0, 10);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedPage, response.getBody());
        verify(cuidadorService).deleteServicio(cuidadorId, servicioId);
    }

    @Test
    void testCrearHospedaje() {
        Long cuidadorId = 1L;
        HospedajeRequestDto requestDto = new HospedajeRequestDto();
        Hospedaje hospedaje = new Hospedaje();
        HospedajeResponseDto expectedResponse = new HospedajeResponseDto();
        when(cuidadorService.crearHospedaje(cuidadorId, requestDto)).thenReturn(hospedaje);
        when(modelMapper.map(hospedaje, HospedajeResponseDto.class)).thenReturn(expectedResponse);

        ResponseEntity<HospedajeResponseDto> response = cuidadorController.crearhopedaje(cuidadorId, requestDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedResponse, response.getBody());
    }

    @Test
    void testAgregarRecomendacion() {
        Long cuidadorId = 1L;
        RecomendacionDto recomendacionDto = new RecomendacionDto();
        Page<Recomendacion> recomendacionPage = new PageImpl<>(Arrays.asList(new Recomendacion()));
        Page<RecomendacionDto> expectedPage = new PageImpl<>(Arrays.asList(new RecomendacionDto()));
        when(cuidadorService.getRecomendacionesPaginadas(cuidadorId, 0, 10)).thenReturn(recomendacionPage);
        when(modelMapper.map(any(Recomendacion.class), eq(RecomendacionDto.class))).thenReturn(new RecomendacionDto());

        ResponseEntity<Page<RecomendacionDto>> response = cuidadorController.agregarRecomendacion(cuidadorId, recomendacionDto, 0, 10);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedPage.getContent().size(), response.getBody().getContent().size());
        verify(cuidadorService).agregarRecomendacion(cuidadorId, recomendacionDto);
    }
}