package com.example.du_an_md6.utils;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class MapperConfig {

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

}
