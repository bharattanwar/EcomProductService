package com.example.demo.client.FakeStoreDTOs;

import com.example.demo.entity.Category;
import com.example.demo.entity.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreProductResponseDTO {
    private long id;
    private String title;
    private double price;
    private String description;
    private String category;
    private String image;
    private FakeStoreProductRatingDTO rating;

}
