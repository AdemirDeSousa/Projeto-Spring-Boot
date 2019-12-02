package com.iau.projeto.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.iau.projeto.domain.Produto;
import com.iau.projeto.dto.ProdutoDTO;
import com.iau.projeto.resources.utils.URL;
import com.iau.projeto.services.ProdutoService;

@RestController	
@RequestMapping(value = "/produtos")
public class ProdutoResource {
	
	//Objeto ProdutoService + Instancia
	@Autowired
	private ProdutoService produto_service;
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Produto> find(@PathVariable Integer id) {
		
		Produto obj = produto_service.find(id);
		
		return ResponseEntity.ok().body(obj);
		
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Page<ProdutoDTO>> findPage(@RequestParam(value = "nome", defaultValue = "") String nome, @RequestParam(value = "categorias", defaultValue = "") String categorias, @RequestParam(value = "pagina", defaultValue = "0") Integer pagina, @RequestParam(value = "linhas_por_pagina", defaultValue = "24") Integer linhas_por_pagina, @RequestParam(value = "ordenar_por", defaultValue = "nome") String ordenar_por, @RequestParam(value = "direcao", defaultValue = "ASC") String direcao) {
		String nomeDecoded = URL.decodeParam(nome);
		List<Integer> ids = URL.decodeIntList(categorias);
	
		Page<Produto> lista = produto_service.search(nomeDecoded, ids, pagina, linhas_por_pagina, ordenar_por, direcao);
		Page<ProdutoDTO> listaDTO = lista.map(obj -> new ProdutoDTO(obj));
		return ResponseEntity.ok().body(listaDTO);	
	}
}
