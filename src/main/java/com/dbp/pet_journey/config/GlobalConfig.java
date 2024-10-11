package com.dbp.pet_journey.config;

import org.modelmapper.ModelMapper;

public class GlobalConfig {
    public ModelMapper modelMappe(){
        return new ModelMapper();
    }
}
