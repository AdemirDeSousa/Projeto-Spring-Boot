package com.iau.projeto.dto;

import java.io.Serializable;

public class CredenciaisDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	//Atributos
	private String email;
	private String senha;
	
	//Construtor Vazio
	public CredenciaisDTO() {
		
	}
	
	
	
	
	//Getters e Setters
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	
}
