package com.example.demo.service;

import com.example.demo.dto.CreateProductRequestDTO;
import com.example.demo.dto.ProductResponseDTO;
import com.example.demo.exception.ProductNotFoundException;
import com.example.demo.entity.Product;

import java.util.List;
import java.util.UUID;

public interface ProductService {
    List<ProductResponseDTO> getAllProducts();
    ProductResponseDTO getProduct(UUID productId) throws ProductNotFoundException;
    ProductResponseDTO createProduct(CreateProductRequestDTO product);
    ProductResponseDTO updateProduct(CreateProductRequestDTO updatedProduct, UUID productId);
    boolean deleteProduct(UUID productId);
    ProductResponseDTO getProduct(String productName);
    List<Product> getProducts(double minPrice, double maxPrice);
}


