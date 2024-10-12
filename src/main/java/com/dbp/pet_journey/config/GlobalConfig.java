package com.dbp.pet_journey.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GlobalConfig {

    @Bean
    public ModelMapper modelMappe(){
        return new ModelMapper();
    }
}
