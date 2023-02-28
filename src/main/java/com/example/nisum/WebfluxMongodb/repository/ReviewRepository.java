package com.example.nisum.WebfluxMongodb.repository;

import com.example.nisum.WebfluxMongodb.entity.Product;
import com.example.nisum.WebfluxMongodb.entity.Promotion;
import com.example.nisum.WebfluxMongodb.entity.Review;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Repository
public interface ReviewRepository extends ReactiveMongoRepository<Review,String> {

    @Query("{productId: ?0}")
    Flux<Review> findByProductId(String productId);
}
