package com.example.demo.dtos;

import com.example.demo.models.Category;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProductDto {
    private Long productId;
    private String title;
    private double price;
    private String description;
    private String image;
    private String category;
    private double rating;
}
