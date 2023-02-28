package com.example.nisum.WebfluxMongodb.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
@AllArgsConstructor(staticName = "create")
public class ProductAggregate {

    private Product product;
    private Promotion promotion;
    private List<Review> reviews;

}