package com.douglas.cursomc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.douglas.cursomc.domain.PaymentBill;
import com.douglas.cursomc.domain.PaymentCard;
import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class JacksonConfig {
	
	@Bean
	public Jackson2ObjectMapperBuilder objectMapperBuilder() {
		Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder() {
			public void configure(ObjectMapper mapper) {
				mapper.registerSubtypes(PaymentCard.class);
				mapper.registerSubtypes(PaymentBill.class);
				super.configure(mapper);
			}
		};
		
		return builder;
	}
}
