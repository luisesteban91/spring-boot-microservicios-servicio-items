package com.formacionbdi.springboot.app.item;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
//import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.EnableFeignClients;


/*
 * @RibbonClient: configurar ribbon
 * name: debe ser igual al cliente feign
 * 
 * @EnableFeignClients
 * habilitar nuestros clientes feign que tengamos inyectados en el 
 * proyecto
 * 
 * @EnableEurekaClient: habilitar eureka cliente
 * 
 * @EnableCircuitBreaker: habilitar cortocircuito de hystrix, 
 * que lo que hace es manejar fallos como, tolerancia a 
 * fallos, baja latencia, time-out, etc. Hystrix es una version 
 * vieja, la actual es resilience4j
 * 
 */

//@RibbonClient(name = "servicio-productos")}
@EnableCircuitBreaker
@EnableEurekaClient
@EnableFeignClients 
@SpringBootApplication
public class SpringbootServicioItemApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootServicioItemApplication.class, args);
	}

}
