package com.example.demo.services;

import com.example.demo.dtos.FakeStoreProductResponseDto;
import com.example.demo.exceptions.ProductNotFoundException;
import com.example.demo.models.Product;
import com.example.demo.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("productserviceimpl")
@Primary
public class ProductServiceImpl implements ProductService{
    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<FakeStoreProductResponseDto> getAllProducts() {
        return List.of();
    }

    @Override
    public FakeStoreProductResponseDto getSingleProduct(Long productId) throws ProductNotFoundException {
        return null;
    }

    @Override
    public Product addNewProduct(Product product) {
        Product savedProduct = productRepository.save(product);
        return savedProduct;
    }

    @Override
    public Product updateProduct(Long productId, Product product) {
        return null;
    }

    @Override
    public boolean deleteProduct(Long productId) {
        return false;
    }
}
