package com.example.demo.dto;

import com.example.demo.entity.BaseModel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProductDTO extends BaseModel {
    private String title;
    private String description;
    private String image;
    private double price;
    private String category;//category of a product
    private RatingDTO ratingDTO;
}