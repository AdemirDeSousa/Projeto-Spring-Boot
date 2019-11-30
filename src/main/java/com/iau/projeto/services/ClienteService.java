package com.iau.projeto.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iau.projeto.domain.Cliente;
import com.iau.projeto.repositories.ClienteRepository;
import com.iau.projeto.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
	
	//Instanciando dependencia pelo mecanismo de injeçao
	@Autowired
	//Objeto do tipo ClienteRepository
	private ClienteRepository categoria_repository;
	
	public Cliente find(Integer id) {
		Optional<Cliente> obj = categoria_repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
		}
}
