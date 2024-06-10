package com.example.demo.service;

import com.example.demo.dto.ProductResponseDTO;
import com.example.demo.entity.Product;

import java.util.List;

public interface ProductService {
    Product getProductDetails(Long userId,Long productId);

    List<Product>  getProducts();

    Product getProduct(long id);

    Product createProduct(Product product);

    Product updateProduct(Long id, Product product);

    String deleteProduct(Long id);

    void putProduct(Long id,Product product);
}


