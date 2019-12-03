package com.iau.projeto.domain.enums;

public enum Perfil {
	
	ADEMIR(1, "ROLE_ADMIN"), CLIENTE(2, "ROLE_CLIENTE");
	
	//Atributos-----------------------------------------------------------------------
	private int codigo;
	private String descricao;
	
	//Contrutor com atributos---------------------------------------------------------
	private Perfil(int codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}
	
	//Getters-------------------------------------------------------------------------
	public int getCodigo() {
		return codigo;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	//Recebe o numero inteiro do codigo e converte para o Valor
	public static Perfil toEnum(Integer codigo) {
		if(codigo == null) {
			return null;
		}
		
		for(Perfil x : Perfil.values()){
			if(codigo.equals(x.getCodigo())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Id invalido: " + codigo);
	}
}	
