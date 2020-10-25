package com.trainingSpring.core;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//componente spring com objetivo de confirguração de Beans
@Configuration
public class ModelMapperConfig {

	//Criando bean do tipo ModelMapper injeção para conseguir usar o autowired
	
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
	
}
