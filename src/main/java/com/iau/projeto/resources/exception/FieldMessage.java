package com.iau.projeto.resources.exception;

import java.io.Serializable;

public class FieldMessage implements Serializable{
	private static final long serialVersionUID = 1L;
	
	//Atributos 
	private String fieldName;
	private String mensagem;
	
	//Contrutor Vazio
	public FieldMessage() {
		
	}
	
	//Construtor com Atributos
	public FieldMessage(String fieldName, String mensagem) {
		super();
		this.fieldName = fieldName;
		this.mensagem = mensagem;
	}
	
	//Getters e Setters	
	public String getFieldname() {
		return fieldName;
	}

	public void setFieldname(String fieldname) {
		this.fieldName = fieldname;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	
	
}
