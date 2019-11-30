package com.iau.projeto;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.iau.projeto.domain.Categoria;
import com.iau.projeto.domain.Produto;
import com.iau.projeto.repositories.CategoriaRepository;
import com.iau.projeto.repositories.ProdutoRepository;

@SpringBootApplication
public class ProjetoSprigApplication implements CommandLineRunner{
	
	//Dependencia
	@Autowired
	private CategoriaRepository categoria_repository;
	
	@Autowired
	private ProdutoRepository produto_repository;
	
	public static void main(String[] args) {
		SpringApplication.run(ProjetoSprigApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		//Objetos categoria
		Categoria cat_1 = new Categoria(null, "Informatica");
		Categoria cat_2 = new Categoria(null, "Escritorio");
		
		//Objetos produto
		Produto p1 = new Produto (null, "Computador", 2000.00);
		Produto p2 = new Produto (null, "Impressora", 800.00);
		Produto p3 = new Produto (null, "Mouse", 80.00);
		
		//Associaçao
		cat_1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat_2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat_1));
		p2.getCategorias().addAll(Arrays.asList(cat_1, cat_2));
		p3.getCategorias().addAll(Arrays.asList(cat_1));
		
		//Salvar objetos no banco de dados
		categoria_repository.saveAll(Arrays.asList(cat_1, cat_2));
		produto_repository.saveAll(Arrays.asList(p1, p2, p3));
	}

}
