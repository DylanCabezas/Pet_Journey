package com.dbp.pet_journey;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GlobalConfig {

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
}
