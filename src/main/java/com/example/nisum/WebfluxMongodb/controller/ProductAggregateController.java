package com.example.nisum.WebfluxMongodb.controller;

import com.example.nisum.WebfluxMongodb.client.ProductAggregateService;
import com.example.nisum.WebfluxMongodb.entity.ProductAggregate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/aggregate")
public class ProductAggregateController {

    @Autowired
    ProductAggregateService productAggregateService;

    @GetMapping("{productId}")
    public Mono<ResponseEntity<ProductAggregate>> getProductAggregate(@PathVariable String productId) {
        return productAggregateService.getProduct(productId).map(e -> ResponseEntity.ok(e)).defaultIfEmpty(ResponseEntity.notFound().build());

    }
}
