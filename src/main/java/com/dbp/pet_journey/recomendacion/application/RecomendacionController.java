package com.dbp.pet_journey.recomendacion.application;

import com.dbp.pet_journey.recomendacion.domain.Recomendacion;
import com.dbp.pet_journey.recomendacion.domain.RecomendacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/recomendacion")
public class RecomendacionController {
    @Autowired
    private RecomendacionService recomendacionService;

    @GetMapping("/obtener_recomendaciones")
    public List<Recomendacion> obtenerRecomendaciones() {
        return recomendacionService.obtenerTodasLasRecomendaciones();
    }

}
