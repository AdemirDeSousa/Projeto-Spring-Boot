package com.iau.projeto.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.iau.projeto.domain.Pedido;
import com.iau.projeto.services.PedidoService;

@RestController	
@RequestMapping(value = "/pedidos")
public class PedidoResource {
	
	//Objeto PedidoService + Instancia
	@Autowired
	private PedidoService pedido_service;
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> Find(@PathVariable Integer id) {
		
		Pedido obj = pedido_service.Find(id);
		
		return ResponseEntity.ok().body(obj);
		
	}
}