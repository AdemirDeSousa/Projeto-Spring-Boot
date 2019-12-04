package com.iau.projeto.security;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.iau.projeto.domain.enums.Perfil;

public class UserSS implements UserDetails{
	private static final long serialVersionUID = 1L;
	
	//Atributos
	private Integer id;
	private String email;
	private String senha;
	
	//Lista de perfis
	private Collection<? extends GrantedAuthority> authorities;
	
	//Construtor Vazio
	public UserSS() {
		
	}
	
	//Construtor com Atributos
	public UserSS(Integer id, String email, String senha, Set<Perfil> perfis) {
		super();
		this.id = id;
		this.email = email;
		this.senha = senha;
		this.authorities = perfis.stream().map(x -> new SimpleGrantedAuthority(x.getDescricao())).collect(Collectors.toList());
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public Integer getId() {
		return id;
	}
	@Override
	public String getPassword() {
		return senha;
	}

	@Override
	public String getUsername() {
		return email;
	}
	

	@Override
	public boolean isAccountNonExpired() {	//VERIFICAR SE A CONTA TA EXPIRADA
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {	//VERIFICAR SE A CONTA ESTA BLOQUEADA
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {	//VERIFICAR SE AS CREDENCIAIS ESTAO EXPIRADAS
		return true;
	}

	@Override
	public boolean isEnabled() {	//Verificar se o Usuario esta ativo
		return true;
	}

}
