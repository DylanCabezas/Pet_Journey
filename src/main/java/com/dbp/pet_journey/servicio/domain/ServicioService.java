package com.dbp.pet_journey.servicio.domain;

import com.dbp.pet_journey.cuidador.domain.Cuidador;
import com.dbp.pet_journey.servicio.dto.ServicioRequestDto;
import com.dbp.pet_journey.servicio.infraestructure.ServicioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicioService {

    @Autowired
    private ServicioRepository servicioRepository;

    public Servicio postServicio(ServicioRequestDto servicioRequestDto, Cuidador cuidador) {
        Servicio servicio = new Servicio();
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.map(servicioRequestDto, servicio);
        servicio.setCuidador(cuidador);
        servicio.setHospedaje(cuidador.getHospedaje());
        servicioRepository.save(servicio);
        return servicio;
    }
}
