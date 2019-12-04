package com.iau.projeto.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.iau.projeto.domain.Cliente;
import com.iau.projeto.dto.ClienteDTO;
import com.iau.projeto.dto.ClienteNewDTO;
import com.iau.projeto.services.ClienteService;

@RestController	
@RequestMapping(value = "/clientes")
public class ClienteResource {
	
	//Objeto ClienteService + Instancia
	@Autowired
	private ClienteService cliente_service;
	
	//Metodo Buscar
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Cliente> find(@PathVariable Integer id) {
		
		Cliente obj = cliente_service.find(id);
		
		return ResponseEntity.ok().body(obj);
		
	}
	
	//Metodo Inserir
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody ClienteNewDTO objDTO){
		Cliente obj = cliente_service.fromDTO(objDTO);
		
		obj = cliente_service.insert(obj);
		URI uri =  ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	//Metodo Update
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody ClienteDTO objDTO, @PathVariable Integer id){
		Cliente obj = cliente_service.fromDTO(objDTO);
		obj.setId(id);
		obj = cliente_service.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	//Metodo Delete
	@PreAuthorize("hasAnyRole('ADMIN')") //DEFINIR QUE SO O ADM PODE UTILIZAR
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id){
		cliente_service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	//Metodo Exibir Todos
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<ClienteDTO>> findAll() {
		List<Cliente> lista = cliente_service.findAll();
		List<ClienteDTO> listaDTO = lista.stream().map(obj -> new ClienteDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listaDTO);	
	}
	
	@RequestMapping(value = "/pagina", method = RequestMethod.GET)
	public ResponseEntity<Page<ClienteDTO>> findPage(@RequestParam(value = "pagina", defaultValue = "0") Integer pagina, @RequestParam(value = "linhas_por_pagina", defaultValue = "24") Integer linhas_por_pagina, @RequestParam(value = "ordenar_por", defaultValue = "nome") String ordenar_por, @RequestParam(value = "direcao", defaultValue = "ASC") String direcao) {
		Page<Cliente> lista = cliente_service.findPage(pagina, linhas_por_pagina, ordenar_por, direcao);
		Page<ClienteDTO> listaDTO = lista.map(obj -> new ClienteDTO(obj));
		return ResponseEntity.ok().body(listaDTO);	
	}

}
