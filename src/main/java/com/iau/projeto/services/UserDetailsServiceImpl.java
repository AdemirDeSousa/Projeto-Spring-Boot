package com.iau.projeto.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.iau.projeto.domain.Cliente;
import com.iau.projeto.repositories.ClienteRepository;
import com.iau.projeto.security.UserSS;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	
	//Injeçao de dependencias
	@Autowired
	private ClienteRepository cliente_repository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Cliente cliente = cliente_repository.findByEmail(email);
		if(cliente == null) {
			throw new UsernameNotFoundException(email);
		}
		return new UserSS(cliente.getId(), cliente.getEmail(), cliente.getSenha(), cliente.getPerfis());
	}

}
