package com.dbp.pet_journey.recomendacion.domain;

import com.dbp.pet_journey.usuario.domain.Usuario;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

<<<<<<< HEAD
=======
import java.util.List;
>>>>>>> 10f2ccd81bea4d5ace4871703483b07f43b90f14
@Setter
@Getter
@Entity
public class Recomendacion {
    @Id
<<<<<<< HEAD
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
=======
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

>>>>>>> 10f2ccd81bea4d5ace4871703483b07f43b90f14
    private String name;

    private String description;

    private String type;

    private String location;

    @ManyToOne
    private Usuario usuario;
}
