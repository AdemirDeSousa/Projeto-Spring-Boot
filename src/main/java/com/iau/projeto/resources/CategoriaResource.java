package com.iau.projeto.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.iau.projeto.domain.Categoria;

@RestController	
@RequestMapping(value = "/categorias")
public class CategoriaResource {
	
	@RequestMapping(method = RequestMethod.GET)
	public List<Categoria> Listar() {
		
		Categoria cat_1 = new Categoria(1, "Informatica");
		Categoria cat_2 = new Categoria(2, "Escritorio");
		
		List<Categoria> lista_categoria = new ArrayList();
		
		lista_categoria.add(cat_1);
		lista_categoria.add(cat_2);
		
		return lista_categoria;
	}
}
