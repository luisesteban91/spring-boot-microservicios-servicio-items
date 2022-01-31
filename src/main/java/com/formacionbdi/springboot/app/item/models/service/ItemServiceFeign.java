package com.formacionbdi.springboot.app.item.models.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.formacionbdi.springboot.app.item.clientes.ProductoClienteRest;
import com.formacionbdi.springboot.app.item.models.Item;
import com.formacionbdi.springboot.app.item.models.Producto;

@Service("serviceFeign")
/*
 * @Primary: como se realizo de otra forma conectarnos a productos
 * se crearon dos servicios que implementan IItemService  y es necesario indicar que este es el primario
 */
@Primary
public class ItemServiceFeign implements IItemService {

	/*
	 * inyectar el ProductoClienteRest creado con feign para
	 * la conexion entre servicios 
	 */
	@Autowired
	private ProductoClienteRest clientFeign;
	
	@Override
	public List<Item> findAll() {
		
		/*
		 * clientFeign.listar(): obtener una lista  de productos desde el microservicio productos
		 * que me retornara un array de productos y necesito convertirlo a 
		 * una lista de productos.
		 * 
		 */
		
		
		/*
		 * convertir la collection de productos a list de productos
		 * 
		 */
		return clientFeign.listar().stream().map(prod -> new Item(prod, 1)).collect(Collectors.toList());
	}

	@Override
	public Item findById(Long id, Integer cantidad) {
		/*
		 * retornar un item(producto, cantidad)
		 */
		return new Item(clientFeign.detalle(id), cantidad);
	}

}
