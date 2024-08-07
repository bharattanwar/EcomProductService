package com.example.demo.Services;

import com.example.demo.entity.Product;

import java.util.List;

public interface IProductServices {
    Product getProductDetails(Long userId, Long productId);

    List<Product> getProducts();

    Product getProduct(long id);

    Product createProduct(Product product);

    Product updateProduct(Long id, Product product);

    String deleteProduct(Long id);

    void putProduct(Long id,Product product);
}

