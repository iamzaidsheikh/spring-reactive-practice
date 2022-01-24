package io.github.iamzaidsheikh.springreactivepractice.database_connectivity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document
@Data
public class Product {
    
    @Id
    private String id;

    private String name;

    private long quantity;

    private double price;
}
