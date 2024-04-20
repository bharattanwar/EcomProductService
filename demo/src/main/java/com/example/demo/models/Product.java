package com.example.demo.models;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Entity
public class Product extends BaseModel{
    private String title;
    private double price;
    private String description;
    private String category;
    private String imageURL;
    private double rating;

}

