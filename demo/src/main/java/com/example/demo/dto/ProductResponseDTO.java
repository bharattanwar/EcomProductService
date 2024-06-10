package com.example.demo.dto;

import com.example.demo.entity.Category;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductResponseDTO {
    private long productId;
    private String title;
    private double price;
    private String description;
    private String category;
    private String imageURL;
    private double rating;
}
