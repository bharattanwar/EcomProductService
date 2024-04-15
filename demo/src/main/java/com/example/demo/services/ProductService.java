package com.example.demo.services;

import com.example.demo.dtos.FakeStoreProductResponseDto;
import com.example.demo.exceptions.NoProductFoundException;
import com.example.demo.exceptions.ProductNotFoundException;
import com.example.demo.models.Product;

import java.util.List;

public interface  ProductService {
    List<FakeStoreProductResponseDto> getAllProducts() throws NoProductFoundException;

    FakeStoreProductResponseDto getSingleProduct(Long productId) throws ProductNotFoundException;

    Product addNewProduct(Product product);

    Product updateProduct(Long productId, Product product);

    boolean deleteProduct(Long productId);
}



