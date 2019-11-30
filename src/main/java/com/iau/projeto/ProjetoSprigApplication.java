package com.iau.projeto;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.iau.projeto.domain.Categoria;
import com.iau.projeto.repositories.CategoriaRepository;

@SpringBootApplication
public class ProjetoSprigApplication implements CommandLineRunner{
	
	@Autowired
	private CategoriaRepository categoria_repository;
	
	public static void main(String[] args) {
		SpringApplication.run(ProjetoSprigApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		//Objetos
		Categoria cat_1 = new Categoria(null, "Informatica");
		Categoria cat_2 = new Categoria(null, "Escritorio");
		
		//Salvar objetos no banco de dados
		categoria_repository.saveAll(Arrays.asList(cat_1, cat_2));
	}

}
