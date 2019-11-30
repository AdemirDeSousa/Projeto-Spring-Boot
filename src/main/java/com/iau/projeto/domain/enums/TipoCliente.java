package com.iau.projeto.domain.enums;

public enum TipoCliente {
	
	PESSOAFISICA(1, "Pessoa Fisica"), PESSOAJURIDICA(2, "Pessoa Juridica");
	
	private int codigo;
	private String descricao;
	
	//Contrutor
	private TipoCliente(int codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}
	
	//Getters
	public int getCodigo() {
		return codigo;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public static TipoCliente toEnum(Integer codigo) {
		if(codigo == null) {
			return null;
		}
		
		for(TipoCliente x : TipoCliente.values()){
			if(codigo.equals(x.getCodigo())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Id invalido: " + codigo);
	}
}
