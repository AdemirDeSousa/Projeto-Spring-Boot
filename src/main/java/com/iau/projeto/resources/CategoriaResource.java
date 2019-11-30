package com.iau.projeto.resources;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.iau.projeto.domain.Categoria;
import com.iau.projeto.services.CategoriaService;

@RestController	
@RequestMapping(value = "/categorias")
public class CategoriaResource {
	
	//Objeto CategoriaService + Instancia
	@Autowired
	private CategoriaService categoria_service;
	
	//Metodo de Busca
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> Find(@PathVariable Integer id) {
		
		Categoria obj = categoria_service.Find(id);
		
		return ResponseEntity.ok().body(obj);
		
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody Categoria obj){
		obj = categoria_service.insert(obj);
		URI uri =  ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
}
