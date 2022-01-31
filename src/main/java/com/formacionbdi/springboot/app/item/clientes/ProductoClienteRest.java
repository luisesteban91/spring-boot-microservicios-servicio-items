package com.formacionbdi.springboot.app.item.clientes;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.formacionbdi.springboot.app.item.models.Producto;

/*
 * @FeignClient : para indicar que es un cliente feign
 * name: nombre del servicio declarado en aplication.properties
 * url: dominio del microservicio
 */
@FeignClient(name = "servicio-productos")
public interface ProductoClienteRest {
	
	/*
	 * @GetMapping("/listar"): url del servicio productos
	 */
	@GetMapping("/listar")
	public List<Producto> listar();
	
	/*
	 * @GetMapping("/ver/{id}"): url del servicio productos
	 */
	@GetMapping("/ver/{id}")
	public Producto detalle(@PathVariable Long id);
	
	
}
