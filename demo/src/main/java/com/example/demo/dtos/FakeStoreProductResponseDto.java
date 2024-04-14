package com.example.demo.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreProductResponseDto {
    // here we should provide attributes matching with the keys provided by the fakestoreapi.
    private double id;
    private String title;
    private double price;
    private String description;
    private String category;
    private String imageURL;
    private FakeStoreProductRatingDTO rating;
}
