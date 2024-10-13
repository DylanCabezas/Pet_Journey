package com.dbp.pet_journey.hospedaje.domain;

import com.dbp.pet_journey.Exceptions.ResourceNotFoundException;
import com.dbp.pet_journey.cuidador.infraestructure.CuidadorRepository;
import com.dbp.pet_journey.hospedaje.dto.HospedajeRequestDto;
import com.dbp.pet_journey.hospedaje.infraestructure.HospedajeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HospedajeService {
    @Autowired
    private HospedajeRepository hospedajeRepository;
    @Autowired
    private CuidadorRepository cuidadorRepository;

    public Hospedaje crearHopedaje(HospedajeRequestDto hospedajeRequestDto, Long cuidadorId) {
        Hospedaje hospedaje = new Hospedaje();
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.map(hospedajeRequestDto, hospedaje);
        hospedaje.setCuidador(cuidadorRepository.findById(cuidadorId).orElseThrow(() -> new ResourceNotFoundException("Cuidador no encontrado")));
        hospedajeRepository.save(hospedaje);
        return hospedaje;
    }
}
