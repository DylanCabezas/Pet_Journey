package com.dbp.pet_journey.recomendacion.domain;

import com.dbp.pet_journey.cuidador.domain.Cuidador;
import com.dbp.pet_journey.recomendacion.dto.RecomendacionDto;
import com.dbp.pet_journey.recomendacion.infraestructure.RecomendacionRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecomendacionService {
    @Autowired
    private RecomendacionRepository recomendacionRepository;

    public Recomendacion crearRecomendacion(RecomendacionDto recomendacionDto, Cuidador cuidador) {
        Recomendacion recomendacion = new Recomendacion();
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.map(recomendacionDto, recomendacion);
        recomendacion.setCuidador(cuidador);
        recomendacionRepository.save(recomendacion);
        return recomendacion;
    }

    public RecomendacionService(RecomendacionRepository recomendacionRepository) {
        this.recomendacionRepository = recomendacionRepository;
    }

    // Método para obtener todas las recomendaciones desde la base de datos
    public List<Recomendacion> obtenerTodasLasRecomendaciones() {
        return recomendacionRepository.findAll();
    }
}
