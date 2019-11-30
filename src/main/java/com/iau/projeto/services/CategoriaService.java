package com.iau.projeto.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iau.projeto.domain.Categoria;
import com.iau.projeto.repositories.CategoriaRepository;

@Service
public class CategoriaService {
	
	//Instanciando dependencia pelo mecanismo de injeçao
	@Autowired
	//Objeto do tipo CategoriaRepository
	private CategoriaRepository categoria_repository;
	
	public Categoria Find(Integer id) {
		Optional<Categoria> obj = categoria_repository.findById(id);
		return obj.orElse(null);
		}
}
