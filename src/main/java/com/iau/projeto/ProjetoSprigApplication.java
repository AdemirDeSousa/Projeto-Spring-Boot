package com.iau.projeto;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.iau.projeto.domain.Categoria;
import com.iau.projeto.domain.Cidade;
import com.iau.projeto.domain.Cliente;
import com.iau.projeto.domain.Endereco;
import com.iau.projeto.domain.Estado;
import com.iau.projeto.domain.ItemPedido;
import com.iau.projeto.domain.Pagamento;
import com.iau.projeto.domain.PagamentoComBoleto;
import com.iau.projeto.domain.PagamentoComCartao;
import com.iau.projeto.domain.Pedido;
import com.iau.projeto.domain.Produto;
import com.iau.projeto.domain.enums.EstadoPagamento;
import com.iau.projeto.domain.enums.TipoCliente;
import com.iau.projeto.repositories.CategoriaRepository;
import com.iau.projeto.repositories.CidadeRepository;
import com.iau.projeto.repositories.ClienteRepository;
import com.iau.projeto.repositories.EnderecoRepository;
import com.iau.projeto.repositories.EstadoRepository;
import com.iau.projeto.repositories.ItemPedidoRepository;
import com.iau.projeto.repositories.PagamentoRepository;
import com.iau.projeto.repositories.PedidoRepository;
import com.iau.projeto.repositories.ProdutoRepository;

@SpringBootApplication
public class ProjetoSprigApplication implements CommandLineRunner{
	
	//Dependencias
	@Autowired
	private CategoriaRepository categoria_repository;
	
	@Autowired
	private ProdutoRepository produto_repository;
	
	@Autowired
	private EstadoRepository estado_repository;
	
	@Autowired
	private CidadeRepository cidade_repository;
	
	@Autowired
	private ClienteRepository cliente_repository;
	
	@Autowired
	private EnderecoRepository endereco_repository;
	
	@Autowired
	private PedidoRepository pedido_repository;
	
	@Autowired
	private PagamentoRepository pagamento_repository;
	
	@Autowired
	private ItemPedidoRepository itemPedido_repository;
	
	
	
	public static void main(String[] args) {
		SpringApplication.run(ProjetoSprigApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		//AUX
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		//Objetos Categoria
		Categoria cat_1 = new Categoria(null, "Informatica");
		Categoria cat_2 = new Categoria(null, "Escritorio");
		Categoria cat_3 = new Categoria(null, "Cama mesa e banho");
		Categoria cat_4 = new Categoria(null, "Eletronicos");
		Categoria cat_5 = new Categoria(null, "Jardinagem");
		Categoria cat_6 = new Categoria(null, "Decoraçao");
		Categoria cat_7 = new Categoria(null, "Perfumaria");
		Categoria cat_8 = new Categoria(null, "Comidas");
		
		//Objetos Produto
		Produto p1 = new Produto (null, "Computador", 2000.00);
		Produto p2 = new Produto (null, "Impressora", 800.00);
		Produto p3 = new Produto (null, "Mouse", 80.00);
		
		//Objetos Estado
		Estado estado_1 = new Estado(null, "Minas Gerais");
		Estado estado_2 = new Estado(null, "Sao Paulo");
		
		//Objetos Cidades
		Cidade c1 = new Cidade(null, "Uberlandia", estado_1);
		Cidade c2 = new Cidade(null, "Sao Paulo", estado_2);
		Cidade c3 = new Cidade(null, "Campinas", estado_2);
		
		//Objetos Cliente
		Cliente cliente_1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "11111111111", TipoCliente.PESSOAFISICA);
		cliente_1.getTelefones().addAll(Arrays.asList("999999999", "988888888"));
		
		//Objeto Endereço
		Endereco e1 = new Endereco(null, "Rua Flores", "300", "Apto 303", "Jardim", "58036460", cliente_1, c1);
		Endereco e2 = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Centro", "58036510", cliente_1, c2);
		
		//Objeto Pedido
		Pedido pedido_1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cliente_1, e1);
		Pedido pedido_2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), cliente_1, e2);
		
		//Objeto Pagamento
		Pagamento pagamento_1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, pedido_1, 6);
		pedido_1.setPagamento(pagamento_1);
		Pagamento pagamento_2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, pedido_2, sdf.parse("20/10/2017 00:00"), null);
		pedido_2.setPagamento(pagamento_2);
		
		//Objeto ItemPedido
		ItemPedido ip_1 = new ItemPedido(pedido_1, p1, 0.00, 1, 2000.00);
		ItemPedido ip_2 = new ItemPedido(pedido_1, p3, 0.00, 2, 80.00);
		ItemPedido ip_3 = new ItemPedido(pedido_2, p2, 100.00, 1, 800.00);
		
		//Associaçao
		cat_1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat_2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat_1));
		p2.getCategorias().addAll(Arrays.asList(cat_1, cat_2));
		p3.getCategorias().addAll(Arrays.asList(cat_1));
		
		estado_1.getCidades().addAll(Arrays.asList(c1));
		estado_2.getCidades().addAll(Arrays.asList(c2, c3));
		
		cliente_1.getEnderecos().addAll(Arrays.asList(e1, e2));
		cliente_1.getPedidos().addAll(Arrays.asList(pedido_1, pedido_2));
		
		pedido_1.getItens().addAll(Arrays.asList(ip_1, ip_2));
		pedido_2.getItens().addAll(Arrays.asList(ip_3));
		
		p1.getItens().addAll(Arrays.asList(ip_1));
		p2.getItens().addAll(Arrays.asList(ip_3));
		p3.getItens().addAll(Arrays.asList(ip_2));
		
		//Salvar objetos no banco de dados
		categoria_repository.saveAll(Arrays.asList(cat_1, cat_2, cat_3, cat_4, cat_5, cat_6, cat_7, cat_8));
		produto_repository.saveAll(Arrays.asList(p1, p2, p3));
		estado_repository.saveAll(Arrays.asList(estado_1, estado_2));
		cidade_repository.saveAll(Arrays.asList(c1, c2, c3));
		cliente_repository.saveAll(Arrays.asList(cliente_1));
		endereco_repository.saveAll(Arrays.asList(e1, e2));
		pedido_repository.saveAll(Arrays.asList(pedido_1, pedido_2));
		pagamento_repository.saveAll(Arrays.asList(pagamento_1, pagamento_2));
		itemPedido_repository.saveAll(Arrays.asList(ip_1, ip_2, ip_3));
		
	}

}
