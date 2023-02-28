package com.example.nisum.WebfluxMongodb.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
@Setter
@Getter
public class Review {

    @Id
    private String id;
    private String user;
    private Integer rating;
    private String comment;
    private String productId;

}
