package com.formacionbdi.springboot.app.item.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.formacionbdi.springboot.app.item.models.Item;
import com.formacionbdi.springboot.app.item.models.Producto;
import com.formacionbdi.springboot.app.item.models.service.IItemService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
public class ItemController {
	
	/*
	 * @Autowired inyectar el ItemServicio
	 * 
	 * La clase ItemService es un servicio que se conecta al
	 * microservicio producto
	 * 
	 * cuando existen mas de un servicio que implementan la 
	 * interface IItemService se necesita declarar en el servicio
	 * cual es el primario. y si no se declara como @Primary, se
	 * puede indicar con @Qualifier("serviceFeign")
	 */
	@Autowired
	@Qualifier("serviceFeign")
	//@Qualifier("serviceRestTemplate")
	private IItemService itemService;
	
	@GetMapping("listar")
	public List<Item> listar(){
		return itemService.findAll();
	}
	
	/*
	 * @HystrixCommand: habilitar tolerancia a fallo con hystrix(cortocircuito)
	 * metodoAlternativo: darle una segunda opcion en caso de fallo
	 */
	@HystrixCommand(fallbackMethod = "metodoAlternativo")
	@GetMapping("/ver/{id}/cantidad/{cantidad}")
	public Item detalle(@PathVariable Long id, @PathVariable Integer cantidad) {
		return itemService.findById(id, cantidad);
	}
	
	public Item metodoAlternativo(Long id, Integer cantidad) {
		Item item = new Item();
		Producto producto = new Producto();
		
		item.setCantidad(cantidad);
		producto.setId(id);
		producto.setNombre("camara Sony");
		producto.setPrecio(600.00);
		item.setProducto(producto);
		
		return item;
	}
}
