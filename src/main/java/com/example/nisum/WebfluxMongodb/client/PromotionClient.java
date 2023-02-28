package com.example.nisum.WebfluxMongodb.client;

import com.example.nisum.WebfluxMongodb.entity.Promotion;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

@Service
public class PromotionClient {

    private WebClient webClient;
    private final Promotion noPromotion = new Promotion("no-promotion", 0.0, LocalDate.of(2999, 12, 31) ,"");

    public PromotionClient(WebClient.Builder webClient) {
        this.webClient = webClient.baseUrl("http://localhost:8080/api/promotion/").build();
    }

    public Mono<Promotion> getPromotion(String productId) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("{productId}")
                        .build(productId))
                .retrieve()
                .bodyToMono(Promotion.class).log()
                .onErrorReturn(noPromotion);
    }
}
