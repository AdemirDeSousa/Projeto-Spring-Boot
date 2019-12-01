package com.iau.projeto.resources.exception;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError {
	private static final long serialVersionUID = 1L;
	
	//Atributos
	private List<FieldMessage> lista = new ArrayList<>();
	
	//Construtor com Atributos
	public ValidationError(Integer status, String msg, Long timeStamp) {
		super(status, msg, timeStamp);
		
	}
	
	//Getters e Setters
	public List<FieldMessage> getErrors() {
		return lista;
	}

	public void addError(String fieldName, String messagem) {
		lista.add(new FieldMessage(fieldName, messagem));
	}

}
