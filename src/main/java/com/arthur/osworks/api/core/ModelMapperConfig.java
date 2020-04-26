package com.arthur.osworks.api.core;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig { // Class de configuração, criada para gerenciar o Model Mapper que não é
                                 // gerênciado pelo Spring pois add a Dependência dele e agora vai ser disponibilizado de injeção nas outras classes

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}