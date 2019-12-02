package com.iau.projeto.services;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iau.projeto.domain.ItemPedido;
import com.iau.projeto.domain.PagamentoComBoleto;
import com.iau.projeto.domain.Pedido;
import com.iau.projeto.domain.enums.EstadoPagamento;
import com.iau.projeto.repositories.ItemPedidoRepository;
import com.iau.projeto.repositories.PagamentoRepository;
import com.iau.projeto.repositories.PedidoRepository;
import com.iau.projeto.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {
	
	//Instanciando dependencia pelo mecanismo de injeçao
	@Autowired
	//Objeto do tipo PedidoRepository
	private PedidoRepository pedido_repository;
	
	@Autowired
	private BoletoService boleto_service;
	
	@Autowired
	private PagamentoRepository pagamento_repository;
	
	@Autowired
	private ProdutoService produto_service;
	
	@Autowired
	private ItemPedidoRepository itemPedido_repository;
	
	public Pedido find(Integer id) {
		Optional<Pedido> obj = pedido_repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
		}
	
	//Metodo Inserir
	@Transactional
	public Pedido insert(Pedido obj) {
		obj.setId(null);
		obj.setInstante(new Date());
		obj.getPagamento().setEstado(EstadoPagamento.PENDENTE);
		obj.getPagamento().setPedido(obj);
		
		if(obj.getPagamento() instanceof PagamentoComBoleto) {
			PagamentoComBoleto pagamento = (PagamentoComBoleto) obj.getPagamento();
			boleto_service.preencherPagamentoComBoleto(pagamento, obj.getInstante());
			}
		obj = pedido_repository.save(obj);
		pagamento_repository.save(obj.getPagamento());
		
		for(ItemPedido ip : obj.getItens()) {
			ip.setDesconto(0.0);
			ip.setPreco(produto_service.find(ip.getProduto().getId()).getPreco());
			ip.setPedido(obj);
		}
		itemPedido_repository.saveAll(obj.getItens());
		return obj;
	}
}
