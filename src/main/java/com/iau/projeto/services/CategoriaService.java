package com.iau.projeto.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.iau.projeto.domain.Categoria;
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
		find(obj.getId());
		return categoria_repository.save(obj);
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
}
