package com.iau.projeto.domain;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.iau.projeto.domain.enums.EstadoPagamento;

@Entity
@JsonTypeName("pagamentoComCartao")
public class PagamentoComCartao extends Pagamento{
	private static final long serialVersionUID = 1L;
	
	//Atributos-----------------------------------------------------------------------
	private Integer numero_de_parcelas;
	
	//Construtor Vazio----------------------------------------------------------------
	public PagamentoComCartao() {
		
	}
	
	//Contrutor com atributos---------------------------------------------------------
	public PagamentoComCartao(Integer id, EstadoPagamento estado, Pedido pedido, Integer numero_de_parcelas) {
		super(id, estado, pedido);
		this.numero_de_parcelas = numero_de_parcelas;
	}
	
	//Getters e Setters---------------------------------------------------------------
	public Integer getNumero_de_parcelas() {
		return numero_de_parcelas;
	}

	public void setNumero_de_parcelas(Integer numero_de_parcelas) {
		this.numero_de_parcelas = numero_de_parcelas;
	}
	
	
	
}
