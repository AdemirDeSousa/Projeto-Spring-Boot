package com.iau.projeto.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.iau.projeto.domain.enums.EstadoPagamento;

@Entity
@JsonTypeName("pagamentoComBoleto")
public class PagamentoComBoleto extends Pagamento{
	private static final long serialVersionUID = 1L;
	
	//Atributos-----------------------------------------------------------------------
	@Temporal(TemporalType.DATE)
	@JsonFormat(pattern="dd/MM/yyyy")
	private Date data_vencimento;
	
	@Temporal(TemporalType.DATE)
	@JsonFormat(pattern="dd/MM/yyyy")
	private Date data_pagamento;
	
	//Construtor Vazio----------------------------------------------------------------
	public PagamentoComBoleto() {
		
	}
	
	//Contrutor com atributos---------------------------------------------------------
	public PagamentoComBoleto(Integer id, EstadoPagamento estado, Pedido pedido, Date data_vencimento, Date data_pagamento) {
		super(id, estado, pedido);
		this.data_pagamento = data_pagamento;
		this.data_vencimento = data_vencimento;
	}
	
	//Getters e Setters---------------------------------------------------------------
	public Date getData_vencimento() {
		return data_vencimento;
	}

	public void setData_vencimento(Date data_vencimento) {
		this.data_vencimento = data_vencimento;
	}

	public Date getData_pagamento() {
		return data_pagamento;
	}

	public void setData_pagamento(Date data_pagamento) {
		this.data_pagamento = data_pagamento;
	}
	
	
	
	
}
