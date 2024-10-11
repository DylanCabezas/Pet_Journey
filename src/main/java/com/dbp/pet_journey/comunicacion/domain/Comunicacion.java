package com.dbp.pet_journey.comunicacion.domain;

<<<<<<< HEAD
import com.dbp.pet_journey.cuidadores.domain.Cuidadores;
=======
import com.dbp.pet_journey.cuidador.domain.Cuidador;
>>>>>>> 10f2ccd81bea4d5ace4871703483b07f43b90f14
import com.dbp.pet_journey.usuario.domain.Usuario;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
<<<<<<< HEAD

@Getter
@Setter
@Entity

public class Comunicacion {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String mensaje;

    private LocalDate fecha_ingreso;

    private LocalDate fecha_salida;
=======
@Getter
@Setter
@Entity
public class Comunicacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String message;

    private LocalDate sendDate;
>>>>>>> 10f2ccd81bea4d5ace4871703483b07f43b90f14

    @ManyToMany
    private List<Usuario> usuarios;

    @ManyToMany
<<<<<<< HEAD
    private List<Cuidadores> cuidadores;
=======
    private List<Cuidador> cuidadores;
>>>>>>> 10f2ccd81bea4d5ace4871703483b07f43b90f14
}
