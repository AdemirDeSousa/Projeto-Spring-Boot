package com.iau.projeto.dto;

import java.io.Serializable;

public class ClienteNewDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	//Atributos
	private String nome;
	private String email;
	private String cpf_ou_cnjp;
	private Integer tipo_cliente;
	
	private String logradouro;
	private String numero;
	private String complemento;
	private String bairro;
	private String cep;
	
	private String telefone1;
	private String telefone2;
	private String telefone3;
	
	private Integer cidade_id;
	
	//Construtor Vazio
	public ClienteNewDTO() {
		
	}
	
	//Getters e Setters
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpf_ou_cnjp() {
		return cpf_ou_cnjp;
	}

	public void setCpf_ou_cnjp(String cpf_ou_cnjp) {
		this.cpf_ou_cnjp = cpf_ou_cnjp;
	}

	public Integer getTipo_cliente() {
		return tipo_cliente;
	}

	public void setTipo_cliente(Integer tipo_cliente) {
		this.tipo_cliente = tipo_cliente;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getTelefone1() {
		return telefone1;
	}

	public void setTelefone1(String telefone1) {
		this.telefone1 = telefone1;
	}

	public String getTelefone2() {
		return telefone2;
	}

	public void setTelefone2(String telefone2) {
		this.telefone2 = telefone2;
	}

	public String getTelefone3() {
		return telefone3;
	}

	public void setTelefone3(String telefone3) {
		this.telefone3 = telefone3;
	}

	public Integer getCidade_id() {
		return cidade_id;
	}

	public void setCidade_id(Integer cidade_id) {
		this.cidade_id = cidade_id;
	}
	
	
}
