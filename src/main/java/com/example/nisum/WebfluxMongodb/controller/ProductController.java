package com.example.nisum.WebfluxMongodb.controller;

import com.example.nisum.WebfluxMongodb.entity.Product;
import com.example.nisum.WebfluxMongodb.entity.Promotion;
import com.example.nisum.WebfluxMongodb.entity.Review;
import com.example.nisum.WebfluxMongodb.repository.ProductRepository;
import com.example.nisum.WebfluxMongodb.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    ProductRepository productRepository;

    @PostMapping("/save")
    public Mono<Product> save(@RequestBody Product product) {
        return productRepository.save(product);
    }

    @GetMapping("/{productId}")
    public Mono<Product> getById(@PathVariable String productId) {
        return productRepository.findById(productId);
    }

}
