package com.dbp.pet_journey.servicio.domain;

import com.dbp.pet_journey.mascota.domain.Mascota;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
<<<<<<< HEAD

=======
>>>>>>> 10f2ccd81bea4d5ace4871703483b07f43b90f14
@Setter
@Getter
@Entity
public class Servicio {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

<<<<<<< HEAD

=======
>>>>>>> 10f2ccd81bea4d5ace4871703483b07f43b90f14
    private String nombre;

    private String description;

    private Double price;

    private EstadoServicio estado;

    @ManyToMany
    private List<Mascota> mascotas;
<<<<<<< HEAD
=======


>>>>>>> 10f2ccd81bea4d5ace4871703483b07f43b90f14
}
