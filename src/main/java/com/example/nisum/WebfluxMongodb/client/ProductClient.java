package com.example.nisum.WebfluxMongodb.client;

import com.example.nisum.WebfluxMongodb.entity.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Collections;

@Service
public class ProductClient {

    private WebClient webClient;

    public ProductClient(WebClient.Builder webClient) {
        this.webClient = webClient.baseUrl("http://localhost:8080/api/product/").build();
    }

    public Mono<Product> getProduct(String productId) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("{productId}")
                        .build(productId))
                .retrieve()
                .bodyToMono(Product.class).log().onErrorResume(e -> Mono.empty());
    }
}
