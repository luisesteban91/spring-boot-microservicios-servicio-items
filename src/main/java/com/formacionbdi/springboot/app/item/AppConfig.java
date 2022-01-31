package com.formacionbdi.springboot.app.item;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/*
 * Permite crear beans(objectos) que son metodos para configuracion
 */
@Configuration
public class AppConfig {
	
	/*
	 * RestTemplate: Es un cliente rest http para acceder recursos
	 * de otros microservicios
	 * 
	 * @LoadBalanced: de forma automatica va a configurar 
	 * el valanceador de carga con ribbon
	 */
	@Bean("clienteRest")
	@LoadBalanced
	public RestTemplate registrarRestTemplate() {
		return new RestTemplate();
	}
}
