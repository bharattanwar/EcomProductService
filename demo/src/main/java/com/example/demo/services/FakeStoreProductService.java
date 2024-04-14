package com.example.demo.services;

import com.example.demo.client.FakeStoreClient;
import com.example.demo.dtos.FakeStoreProductResponseDto;
import com.example.demo.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FakeStoreProductService implements ProductService{

    @Autowired
    private FakeStoreClient fakeStoreClient;

    @Override
    public List<FakeStoreProductResponseDto> getAllProducts() {
        List<FakeStoreProductResponseDto> fakeStoreProducts = fakeStoreClient.getAllproducts();
        return fakeStoreProducts;
    }

    @Override
    public Product getSingleProduct(Long productId) {
        return null;
    }

    @Override
    public Product addNewProduct(Product product) {
        return null;
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
