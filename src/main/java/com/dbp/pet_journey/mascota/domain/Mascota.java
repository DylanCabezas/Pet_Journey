package com.dbp.pet_journey.mascota.domain;
<<<<<<< HEAD
import com.dbp.pet_journey.cuidadores.domain.Cuidadores;
=======

import com.dbp.pet_journey.cuidador.domain.Cuidador;
>>>>>>> 10f2ccd81bea4d5ace4871703483b07f43b90f14
import com.dbp.pet_journey.hospedaje.domain.Hospedaje;
import com.dbp.pet_journey.servicio.domain.Servicio;
import com.dbp.pet_journey.usuario.domain.Usuario;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
<<<<<<< HEAD

@Setter
@Getter
@Entity

public class Mascota {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private Especie especie;

    private Integer age;

    private double wight;

    private String caracteristics;

    private String animalBreed;
=======
@Getter
@Setter
@Entity
public class Mascota {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String dogName;

    private String dogBreed;

    private Double dogWeight;

    private String dogCaracteristics;

    private Integer dogAge;
>>>>>>> 10f2ccd81bea4d5ace4871703483b07f43b90f14

    @ManyToOne
    private Usuario usuario;

    @ManyToMany
    private List<Servicio> servicios;

    @ManyToOne
    private Hospedaje hospedaje;

    @ManyToOne
<<<<<<< HEAD
    private Cuidadores cuidador;
}
=======
    private Cuidador cuidador;


}
>>>>>>> 10f2ccd81bea4d5ace4871703483b07f43b90f14
