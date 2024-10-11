package com.dbp.pet_journey.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;

public class GlobalConfig {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
