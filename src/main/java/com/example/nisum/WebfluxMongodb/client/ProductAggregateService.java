package com.example.nisum.WebfluxMongodb.client;

import com.example.nisum.WebfluxMongodb.entity.Product;
import com.example.nisum.WebfluxMongodb.entity.ProductAggregate;
import com.example.nisum.WebfluxMongodb.entity.Promotion;
import com.example.nisum.WebfluxMongodb.entity.Review;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple3;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductAggregateService {

    private final ProductClient productClient;
    private final PromotionClient promotionClient;
    private final ReviewClient reviewClient;

    public Mono<ProductAggregate> getProduct(String productId){
        return Mono.zip(productClient.getProduct(productId),promotionClient.getPromotion(productId),reviewClient.getReview(productId)).log().map(this::combine);
    }

    private ProductAggregate combine(Tuple3<Product, Promotion, List<Review>> tuple3) {
        return ProductAggregate.create(tuple3.getT1(),tuple3.getT2(),tuple3.getT3());
    }
}
