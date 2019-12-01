package com.iau.projeto.dto;

import java.io.Serializable;

import com.iau.projeto.domain.Categoria;

public class CategoriaDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	//Atributos
	private Integer id;
	private String nome;
	
	//Construtor vazio
	public CategoriaDTO() {
		
	}
	
	//Construtor recebendo Categoria
	public CategoriaDTO(Categoria obj) {
		id = obj.getId();
		nome = obj.getNome();
	}
	
	//Getters e Setters
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
}
