package com.example.demo.service;

import com.example.demo.dto.FakeStoreProductResponseDTO;
import com.example.demo.exception.ProductNotFoundException;
import com.example.demo.entity.Product;

import java.util.List;
import java.util.UUID;

public interface ProductService {
    List<Product> getAllProducts();
    Product getProduct(UUID productId) throws ProductNotFoundException;
    Product createProduct(Product product);
    Product updateProduct(Product updatedProduct, UUID productId);
    boolean deleteProduct(UUID productId);
    Product getProduct(String productName);
    List<Product> getProducts(double minPrice, double maxPrice);
}


