package com.formacionbdi.springboot.app.item.models.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.formacionbdi.springboot.app.item.models.Item;
import com.formacionbdi.springboot.app.item.models.Producto;

@Service("serviceRestTemplate")
public class ItemServiceImpl implements IItemService {
	
	/*
	 * Inyectar el RestTemplate creado en AppConfig.
	 * RestTemplate: Es un cliente rest http para acceder recursos
	 * de otros microservicios
	 */
	
	@Autowired
	private RestTemplate clienteRest;
	
	@Override
	public List<Item> findAll() {
		/*
		 * obtener una lista  de productos desde el microservicio productos
		 * que me retornara un array de productos y necesito convertirlo a 
		 * una lista de productos.
		 * 
		 * Producto[].class: indicar el tipo de dato en el cual vamos a 
		 * obtener los elementos del json, en este caso en un arreglo de 
		 * Producto.
		 */
		List<Producto> productos = Arrays.asList(
			clienteRest.getForObject(
				"http://servicio-productos/listar", Producto[].class
			)
		);
		
		/*
		 * convertir la collection de productos a list de productos
		 * 
		 */
		return productos.stream().map(prod -> new Item(prod, 1)).collect(Collectors.toList());
	}

	@Override
	public Item findById(Long id_producto, Integer cantidad) {
		/*
		 * Convertir el producto en un Map de variables de id 
		 * de producto, en este caso un map con el id de parametro
		 * 
		 * La Interface Map (java.io.Map) en Java, 
		 * nos permite representar una estructura de datos para 
		 * almacenar pares "clave/valor";
		 */
		Map<String, String> pathVariable = new HashMap<String, String>();
		pathVariable.put("id", id_producto.toString());
		
		Producto producto = clienteRest.getForObject(
				"http://servicio-productos/ver/{id}", 
				Producto.class,
				pathVariable //pasar un pathVariable como tercer parametro un map del id
		);
		
		/*
		 * retornar un item(producto, cantidad)
		 */
		return new Item(producto, cantidad);
	}

}
