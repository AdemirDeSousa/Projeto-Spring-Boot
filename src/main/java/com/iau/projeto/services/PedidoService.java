package com.iau.projeto.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iau.projeto.domain.Pedido;
import com.iau.projeto.repositories.PedidoRepository;
import com.iau.projeto.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {
	
	//Instanciando dependencia pelo mecanismo de injeçao
	@Autowired
	//Objeto do tipo PedidoRepository
	private PedidoRepository pedido_repository;
	
	public Pedido Find(Integer id) {
		Optional<Pedido> obj = pedido_repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
		}
}
