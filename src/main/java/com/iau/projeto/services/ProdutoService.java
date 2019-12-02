package com.iau.projeto.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.iau.projeto.domain.Categoria;
import com.iau.projeto.domain.Produto;
import com.iau.projeto.repositories.CategoriaRepository;
import com.iau.projeto.repositories.ProdutoRepository;
import com.iau.projeto.services.exceptions.ObjectNotFoundException;

@Service
public class ProdutoService {
	
	//Instanciando dependencia pelo mecanismo de injeçao
	@Autowired
	//Objeto do tipo ProdutoRepository
	private ProdutoRepository produto_repository;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public Produto find(Integer id) {
		Optional<Produto> obj = produto_repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Produto.class.getName()));
		}
	
	public Page<Produto> search(String nome, List<Integer> ids, Integer pagina, Integer linhas_por_pagina, String ordenar_por, String direcao){
		PageRequest pageRequest = PageRequest.of(pagina, linhas_por_pagina, Direction.valueOf(direcao), ordenar_por);
		List<Categoria> categorias = categoriaRepository.findAllById(ids);
		return produto_repository.search(nome, categorias, pageRequest);
	}
}
