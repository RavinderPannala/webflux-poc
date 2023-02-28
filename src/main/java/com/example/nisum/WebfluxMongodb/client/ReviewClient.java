package com.example.nisum.WebfluxMongodb.client;

import com.example.nisum.WebfluxMongodb.entity.Review;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.List;

@Service
public class ReviewClient {

    private WebClient webClient;

    public ReviewClient(WebClient.Builder webClient) {
        this.webClient = webClient.baseUrl("http://localhost:8080/api/review/").build();
    }

    public Mono<List<Review>> getReview(String productId) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("{productId}").build(productId))
                .retrieve()
                .bodyToFlux(Review.class)
                .collectList().log()
                .onErrorReturn(Collections.emptyList());
    }
}
