package com.example.nisum.WebfluxMongodb.repository;

import com.example.nisum.WebfluxMongodb.entity.Product;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends ReactiveMongoRepository<Product,String> {
}
