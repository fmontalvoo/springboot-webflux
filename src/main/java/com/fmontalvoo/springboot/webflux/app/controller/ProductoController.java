package com.fmontalvoo.springboot.webflux.app.controller;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.thymeleaf.spring5.context.webflux.ReactiveDataDriverContextVariable;

import com.fmontalvoo.springboot.webflux.app.documents.Producto;
import com.fmontalvoo.springboot.webflux.app.repository.ProductoRepository;

import reactor.core.publisher.Flux;

@Controller
public class ProductoController {

	@Autowired
	private ProductoRepository repository;

	@GetMapping({ "", "/", "/listar" })
	public String listar(Model model) {
		Flux<Producto> productos = repository.findAll().map(producto -> {
			producto.setNombre(producto.getNombre().toUpperCase());
			return producto;
		});

//		productos.subscribe();

		model.addAttribute("productos", productos);
		return "listar";
	}

	@GetMapping("/listar-data")
	public String listarDataDriver(Model model) {
		Flux<Producto> productos = repository.findAll().map(producto -> {
			producto.setNombre(producto.getNombre().toUpperCase());
			return producto;
		}).delayElements(Duration.ofSeconds(1));

		model.addAttribute("productos", new ReactiveDataDriverContextVariable(productos, 1));
		return "listar";
	}

}
