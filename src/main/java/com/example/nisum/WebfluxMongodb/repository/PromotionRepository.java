package com.example.nisum.WebfluxMongodb.repository;

import com.example.nisum.WebfluxMongodb.entity.Employee;
import com.example.nisum.WebfluxMongodb.entity.Product;
import com.example.nisum.WebfluxMongodb.entity.Promotion;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface PromotionRepository extends ReactiveMongoRepository<Promotion,String> {

    @Query("{productId: ?0}")
    Mono<Promotion> findByProductId(String productId);
}
