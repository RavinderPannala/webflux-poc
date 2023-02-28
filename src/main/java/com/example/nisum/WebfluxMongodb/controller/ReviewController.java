package com.example.nisum.WebfluxMongodb.controller;

import com.example.nisum.WebfluxMongodb.entity.Review;
import com.example.nisum.WebfluxMongodb.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/review")
public class ReviewController {

    @Autowired
    private ReviewRepository reviewRepository;
    
    @PostMapping("/save")
    public Mono<Review> save(@RequestBody Review review){
       return reviewRepository.save(review);
    }

    @GetMapping("/{productId}")
    public Flux<Review> getById(@PathVariable String productId){
        return reviewRepository.findByProductId(productId);
    }
}


