package com.fmontalvoo.springboot.webflux.app;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;

import com.fmontalvoo.springboot.webflux.app.documents.Producto;
import com.fmontalvoo.springboot.webflux.app.repository.ProductoRepository;

import reactor.core.publisher.Flux;

@SpringBootApplication
public class SpringbootWebfluxApplication implements CommandLineRunner {

	@Autowired
	private ProductoRepository repository;

	@Autowired
	private ReactiveMongoTemplate mongoTemplate;

	private static final Logger log = LoggerFactory.getLogger(SpringbootWebfluxApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SpringbootWebfluxApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		mongoTemplate.dropCollection(Producto.class).subscribe();
//		mongoTemplate.dropCollection("productos");

		Flux.just(new Producto("Producto#1", 1.5), new Producto("Producto#2", 2.5), new Producto("Producto#3", 3.5),
				new Producto("Producto#4", 4.5), new Producto("Producto#5", 5.5), new Producto("Producto#6", 6.5),
				new Producto("Producto#7", 7.5)).flatMap(producto -> {
					producto.setCreatedAt(new Date());
					return repository.save(producto);
				}).subscribe(producto -> log.info("Poducto: ".concat(producto.getId())));
	}

}
