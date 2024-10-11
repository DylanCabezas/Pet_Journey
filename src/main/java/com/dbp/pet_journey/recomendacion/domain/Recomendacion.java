package com.dbp.pet_journey.recomendacion.domain;

import com.dbp.pet_journey.usuario.domain.Usuario;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Recomendacion {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

    private String description;

    private String type;

    private String location;

    @ManyToOne
    private Usuario usuario;
}
