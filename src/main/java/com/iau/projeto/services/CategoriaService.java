package com.iau.projeto.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.iau.projeto.domain.Categoria;
import com.iau.projeto.dto.CategoriaDTO;
import com.iau.projeto.repositories.CategoriaRepository;
import com.iau.projeto.services.exceptions.DataIntegrityException;
import com.iau.projeto.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {
	
	//Instanciando dependencia pelo mecanismo de injeçao
	@Autowired
	//Objeto do tipo CategoriaRepository
	private CategoriaRepository categoria_repository;
	
	//Metodo Buscas
	public Categoria find(Integer id) {
		Optional<Categoria> obj = categoria_repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
		}
	
	//Metodo Inserir
	public Categoria insert(Categoria obj) {
		obj.setId(null);
		return categoria_repository.save(obj);
	}
	
	//Metodo Atualizar
	public Categoria update(Categoria obj) {
		Categoria newObj = find(obj.getId());
		updateData(newObj, obj);
		return categoria_repository.save(newObj);
	}
	
	//Metodo Deletar
	public void delete(Integer id) {
		find(id);
		
		try {
			categoria_repository.deleteById(id);
		}
		catch (DataIntegrityViolationException e){
			throw new DataIntegrityException("Nao é possivel excluir uma Categoria que possui Produtos");
		}
	}
	
	//Metodo Exibir Todos
	public List<Categoria> findAll(){
		return categoria_repository.findAll();
	}
	
	//Metodo para Exibir as Categorias por paginaçao
	public Page<Categoria> findPage(Integer pagina, Integer linhas_por_pagina, String ordenar_por, String direcao){
		PageRequest pageRequest = PageRequest.of(pagina, linhas_por_pagina, Direction.valueOf(direcao), ordenar_por);
		return categoria_repository.findAll(pageRequest);	
	}
	
	//Metodo para transformar objeto Categoria
	public Categoria fromDTO(CategoriaDTO objDTO) {
		return new Categoria(objDTO.getId(), objDTO.getNome());
	}
	
	//Metodo de ajuda para o metodo Atualizar
	private void updateData(Categoria newObj, Categoria obj){
		newObj.setNome(obj.getNome());	
	}
}
