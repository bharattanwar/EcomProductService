package com.example.demo.mapper;

import com.example.demo.dtos.ProductDto;
import com.example.demo.models.Product;

public class ProductModelDtoMapper {
    public static ProductDto convertProductModelToProductDto(Product product){
        ProductDto productDto = new ProductDto();
        productDto.setTitle(product.getTitle());
        productDto.setPrice(product.getPrice());
        productDto.setImage(product.getImageURL());
        productDto.setDescription(product.getDescription());
        productDto.setCategory(product.getCategory());
        productDto.setRating(product.getRating());
        return productDto;
    }

}
