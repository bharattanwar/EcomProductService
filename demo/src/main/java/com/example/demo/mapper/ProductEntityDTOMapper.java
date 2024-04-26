package com.example.demo.mapper;

import com.example.demo.dto.CreateProductRequestDTO;
import com.example.demo.dto.ProductResponseDTO;
import com.example.demo.entity.Product;

public class ProductEntityDTOMapper {
    public static ProductResponseDTO convertProductEntityToProductResponseDTO(Product product){
        ProductResponseDTO responseDTO = new ProductResponseDTO();
        responseDTO.setTitle(product.getTitle());
        responseDTO.setRating(product.getRating());
        responseDTO.setPrice(product.getPrice());
        responseDTO.setImageURL(product.getImageURL());
        responseDTO.setDescription(product.getDescription());
        responseDTO.setCategory(product.getCategory().getName());
        return responseDTO;
    }
    public static Product convertCreateProductRequestDTOToProduct(CreateProductRequestDTO productRequestDTO){
        Product product = new Product();
        product.setTitle(productRequestDTO.getTitle());
        product.setRating(0);
        product.setPrice(productRequestDTO.getPrice());
        product.setImageURL(productRequestDTO.getImageURL());
        product.setDescription(productRequestDTO.getDescription());
        return product;
    }
}

