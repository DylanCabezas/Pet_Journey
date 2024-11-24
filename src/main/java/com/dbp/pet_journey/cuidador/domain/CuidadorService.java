package com.dbp.pet_journey.cuidador.domain;

import com.dbp.pet_journey.Exceptions.ResourceNotFoundException;
import com.dbp.pet_journey.auth.domain.Role;
import com.dbp.pet_journey.auth.dto.JwtAuthResponse;
import com.dbp.pet_journey.auth.dto.LoginReq;
import com.dbp.pet_journey.config.JwtService;
import com.dbp.pet_journey.cuidador.dto.CuidadorLoginDto;
import com.dbp.pet_journey.cuidador.dto.CuidadorRequestDto;
import com.dbp.pet_journey.cuidador.dto.CuidadorResponseDto;
import com.dbp.pet_journey.cuidador.infraestructure.CuidadorRepository;
import com.dbp.pet_journey.hospedaje.domain.Hospedaje;
import com.dbp.pet_journey.hospedaje.domain.HospedajeService;
import com.dbp.pet_journey.hospedaje.dto.HospedajeRequestDto;
import com.dbp.pet_journey.recomendacion.domain.Recomendacion;
import com.dbp.pet_journey.recomendacion.domain.RecomendacionService;
import com.dbp.pet_journey.recomendacion.dto.RecomendacionDto;
import com.dbp.pet_journey.servicio.domain.Servicio;
import com.dbp.pet_journey.servicio.domain.ServicioService;
import com.dbp.pet_journey.servicio.dto.ServicioRequestDto;
import com.dbp.pet_journey.servicio.infraestructure.ServicioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CuidadorService {
    @Autowired
    private CuidadorRepository cuidadorRepository;
    @Autowired
    private ServicioRepository servicioRepository;
    @Autowired
    private ServicioService servicioService;
    @Autowired
    private HospedajeService hospedajeService;
    @Autowired
    private RecomendacionService recomendacionService;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtService jwtService;
    @Autowired
    AuthenticationManager authenticationManager;

    public JwtAuthResponse register(CuidadorRequestDto cuidadorRequestDto) {
        Cuidador cuidador = new Cuidador();
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.map(cuidadorRequestDto, cuidador);
        cuidador.setRole(Role.CUIDADOR);
        cuidador.setPassword(passwordEncoder.encode(cuidador.getPassword()));
        cuidadorRepository.save(cuidador);
        var jwt = jwtService.generateToken(cuidador);

        JwtAuthResponse response = new JwtAuthResponse();
        response.setToken(jwt);

        return response;
    }

    public JwtAuthResponse login(CuidadorLoginDto request) throws IllegalArgumentException {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        var user = cuidadorRepository.findByEmail(request.getEmail());
        var jwt = jwtService.generateToken(user);

        JwtAuthResponse response = new JwtAuthResponse();
        response.setToken(jwt);

        return response;
    }

    public CuidadorResponseDto getCuidador(Long id) {
        Cuidador cuidador = cuidadorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Cuidador no encontrado"));
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(cuidador, CuidadorResponseDto.class);
    }

    public Cuidador crearServicio(Long cuidadorId, ServicioRequestDto servicioRequestDto) {
        Cuidador cuidador = cuidadorRepository.findById(cuidadorId).orElseThrow(() -> new ResourceNotFoundException("Cuidador no encontrado"));
        Servicio servicio = servicioService.postServicio(servicioRequestDto, cuidador);
        cuidador.getServicios().add(servicio);
        cuidadorRepository.save(cuidador);
        return  cuidador;
    }

    public Cuidador crearHospedaje(Long cuidadorId, HospedajeRequestDto hospedajeRequestDto) {
        Cuidador cuidador = cuidadorRepository.findById(cuidadorId).orElseThrow(() -> new ResourceNotFoundException("Cuidador no encontrado"));
        Hospedaje hospedaje = hospedajeService.crearHopedaje(hospedajeRequestDto, cuidadorId);
        cuidador.setHospedaje(hospedaje);
        cuidadorRepository.save(cuidador);
        return  cuidador;
    }

    public Cuidador deleteServicio(Long cuidadorId, Long servicioId){
        Cuidador cuidador = cuidadorRepository.findById(cuidadorId)
                .orElseThrow(() -> new ResourceNotFoundException("Cuidador no encontrado"));
        Servicio servicio = servicioRepository.findById(servicioId)
                .orElseThrow(() -> new ResourceNotFoundException("Servicio no encontrado"));
        cuidador.getServicios().remove(servicio);
        servicioRepository.delete(servicio);
        return cuidadorRepository.save(cuidador);
    }

    public Cuidador agregarRecomendacion(Long cuidadorId, RecomendacionDto recomendacionDto){
        Cuidador cuidador = cuidadorRepository.findById(cuidadorId).orElseThrow(() -> new ResourceNotFoundException("Cuidador no encontrado"));
        Recomendacion recomendacion = recomendacionService.crearRecomendacion(recomendacionDto,cuidador);
        cuidador.getRecomendaciones().add(recomendacion);
        cuidadorRepository.save(cuidador);
        return cuidador;
    }

    public void deleteCuidador(Long id) {
        cuidadorRepository.deleteById(id);
    }
}
