package com.fmontalvoo.springboot.webflux.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fmontalvoo.springboot.webflux.app.documents.Producto;
import com.fmontalvoo.springboot.webflux.app.repository.ProductoRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/productos")
public class ProductoRestController {
	@Autowired
	private ProductoRepository repository;

	@GetMapping("/{id}")
	public Mono<Producto> obtenerPorId(@PathVariable String id) {
		return repository.findById(id);
	}

	@GetMapping
	public Flux<Producto> listar() {
		return repository.findAll();
	}
}
