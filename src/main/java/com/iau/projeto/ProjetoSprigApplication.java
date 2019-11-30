package com.iau.projeto;

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
import com.iau.projeto.domain.Produto;
import com.iau.projeto.domain.enums.TipoCliente;
import com.iau.projeto.repositories.CategoriaRepository;
import com.iau.projeto.repositories.CidadeRepository;
import com.iau.projeto.repositories.ClienteRepository;
import com.iau.projeto.repositories.EnderecoRepository;
import com.iau.projeto.repositories.EstadoRepository;
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
	
	
	public static void main(String[] args) {
		SpringApplication.run(ProjetoSprigApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		//Objetos Categoria
		Categoria cat_1 = new Categoria(null, "Informatica");
		Categoria cat_2 = new Categoria(null, "Escritorio");
		
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
		
		//Associaçao
		cat_1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat_2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat_1));
		p2.getCategorias().addAll(Arrays.asList(cat_1, cat_2));
		p3.getCategorias().addAll(Arrays.asList(cat_1));
		
		estado_1.getCidades().addAll(Arrays.asList(c1));
		estado_2.getCidades().addAll(Arrays.asList(c2, c3));
		
		cliente_1.getEnderecos().addAll(Arrays.asList(e1, e2));	
		
		//Salvar objetos no banco de dados
		categoria_repository.saveAll(Arrays.asList(cat_1, cat_2));
		produto_repository.saveAll(Arrays.asList(p1, p2, p3));
		estado_repository.saveAll(Arrays.asList(estado_1, estado_2));
		cidade_repository.saveAll(Arrays.asList(c1, c2, c3));
		cliente_repository.saveAll(Arrays.asList(cliente_1));
		endereco_repository.saveAll(Arrays.asList(e1, e2));
	}

}
