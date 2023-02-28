package com.example.nisum.WebfluxMongodb.controller;

import com.example.nisum.WebfluxMongodb.entity.Product;
import com.example.nisum.WebfluxMongodb.entity.Promotion;
import com.example.nisum.WebfluxMongodb.entity.Review;
import com.example.nisum.WebfluxMongodb.repository.PromotionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/promotion")
public class PromotionController {

    @Autowired
    private PromotionRepository promotionRepository;

    private final Promotion noPromotion = new Promotion("no-promotion", 0.0, LocalDate.of(2999, 12, 31) ,"");


    @PostMapping("/save")
    public Mono<Promotion> save(@RequestBody Promotion product) {
        return promotionRepository.save(product);
    }

    @GetMapping("/{productId}")
    public Mono<Promotion> getById(@PathVariable String productId) {
        return promotionRepository.findByProductId(productId).onErrorReturn(noPromotion);
    }
}
