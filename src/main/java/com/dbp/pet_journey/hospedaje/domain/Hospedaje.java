package com.dbp.pet_journey.hospedaje.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Hospedaje {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

<<<<<<< HEAD
    private Integer capacity;

    private String location;
=======
    private String location;

    private Integer capacity;
>>>>>>> 10f2ccd81bea4d5ace4871703483b07f43b90f14

    private String type;
}
