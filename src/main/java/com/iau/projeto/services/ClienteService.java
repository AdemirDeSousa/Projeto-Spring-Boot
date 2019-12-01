package com.iau.projeto.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.iau.projeto.domain.Cliente;
import com.iau.projeto.dto.ClienteDTO;
import com.iau.projeto.repositories.ClienteRepository;
import com.iau.projeto.services.exceptions.DataIntegrityException;
import com.iau.projeto.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
	
	//Instanciando dependencia pelo mecanismo de injeçao
	@Autowired
	//Objeto do tipo ClienteRepository
	private ClienteRepository cliente_repository;
	
	//Metodo Buscar
	public Cliente find(Integer id) {
		Optional<Cliente> obj = cliente_repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
		}
	
	//Metodo Atualizar
	public Cliente update(Cliente obj) {
		Cliente newObj = find(obj.getId());
		updateData(newObj, obj);
		return cliente_repository.save(newObj);
	}
	
	//Metodo Deletar
	public void delete(Integer id) {
		find(id);
		
		try {
			cliente_repository.deleteById(id);
		}
		catch (DataIntegrityViolationException e){
			throw new DataIntegrityException("Nao é possivel excluir porque possui Entidades relacionadas");
		}
	}
	
	//Metodo Exibir Todos
	public List<Cliente> findAll(){
		return cliente_repository.findAll();
	}
	
	//Metodo para Exibir as Clientes por paginaçao
	public Page<Cliente> findPage(Integer pagina, Integer linhas_por_pagina, String ordenar_por, String direcao){
		PageRequest pageRequest = PageRequest.of(pagina, linhas_por_pagina, Direction.valueOf(direcao), ordenar_por);
		return cliente_repository.findAll(pageRequest);	
	}
	
	//Metodo para transformar objeto Cliente
	public Cliente fromDTO(ClienteDTO objDTO) {
		return new Cliente(objDTO.getId(), objDTO.getNome(), objDTO.getEmail(), null, null);
	}
	
	//Metodo 
	private void updateData(Cliente newObj, Cliente obj){
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
		
	}
	
}
