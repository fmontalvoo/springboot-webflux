package com.fmontalvoo.springboot.webflux.app.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.fmontalvoo.springboot.webflux.app.documents.Producto;

public interface ProductoRepository extends ReactiveMongoRepository<Producto, String> {

}
