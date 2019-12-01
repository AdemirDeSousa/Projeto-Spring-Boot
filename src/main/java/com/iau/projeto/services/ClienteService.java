package com.iau.projeto.services;

import java.util.List;
import java.util.Optional;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iau.projeto.domain.Cidade;
import com.iau.projeto.domain.Cliente;
import com.iau.projeto.domain.Endereco;
import com.iau.projeto.domain.enums.TipoCliente;
import com.iau.projeto.dto.ClienteDTO;
import com.iau.projeto.dto.ClienteNewDTO;
import com.iau.projeto.repositories.ClienteRepository;
import com.iau.projeto.repositories.EnderecoRepository;
import com.iau.projeto.services.exceptions.DataIntegrityException;
import com.iau.projeto.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
	
	//Instanciando dependencia pelo mecanismo de injeçao
	@Autowired
	//Objeto do tipo ClienteRepository
	private ClienteRepository cliente_repository;
	
	@Autowired
	private EnderecoRepository endereco_repository;
	
	//Metodo Buscar
	public Cliente find(Integer id) {
		Optional<Cliente> obj = cliente_repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
		}
	
	//Metodo Inserir
	@Transactional
	public Cliente insert(Cliente obj) {
		obj.setId(null);
		obj = cliente_repository.save(obj);
		endereco_repository.saveAll(obj.getEnderecos());
		return obj;
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
	
	//Metodo para transformar objeto Cliente pelo ClienteNewDTO
	public Cliente fromDTO(ClienteNewDTO objDTO) {
		Cliente cliente = new Cliente(null, objDTO.getNome(), objDTO.getEmail(), objDTO.getCpf_ou_cnjp(), TipoCliente.toEnum(objDTO.getTipo_cliente()));
		Cidade cidade = new Cidade(objDTO.getCidade_id(), null, null);
		Endereco endereco = new Endereco(null, objDTO.getLogradouro(), objDTO.getNumero(), objDTO.getComplemento(), objDTO.getBairro(), objDTO.getCep(), cliente, cidade);
		cliente.getEnderecos().add(endereco);
		cliente.getTelefones().add(objDTO.getTelefone1());
		if (objDTO.getTelefone2()!=null) {
			cliente.getTelefones().add(objDTO.getTelefone2());
		}
		if (objDTO.getTelefone3()!=null) {
			cliente.getTelefones().add(objDTO.getTelefone3());
		}
		return cliente;
		
	}
	
	//Metodo de ajuda para o metodo Atualizar
	private void updateData(Cliente newObj, Cliente obj){
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());	
	}
	
}
