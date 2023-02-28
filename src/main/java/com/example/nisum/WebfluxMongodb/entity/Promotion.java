package com.example.nisum.WebfluxMongodb.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@Document
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Promotion {

    @Id
    private String type;
    private Double discount;
    private LocalDate endDate;

    private String productId;
}
