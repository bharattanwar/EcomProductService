package com.example.demo.services;

import com.example.demo.client.FakeStoreClient;
import com.example.demo.dtos.FakeStoreProductResponseDto;
import com.example.demo.exceptions.InvalidProductIdException;
import com.example.demo.exceptions.NoProductFoundException;
import com.example.demo.exceptions.ProductNotFoundException;
import com.example.demo.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("fakestoreproductservice")
public class FakeStoreProductService implements ProductService{

    @Autowired
    private FakeStoreClient fakeStoreClient;

    @Override
    public List<FakeStoreProductResponseDto> getAllProducts(){
        List<FakeStoreProductResponseDto> fakeStoreProducts = fakeStoreClient.getAllproducts();
        if (fakeStoreProducts == null){
            throw new NoProductFoundException("No products are found");
        }
        return fakeStoreProducts;
    }

    @Override
    public FakeStoreProductResponseDto getSingleProduct(Long productId) throws ProductNotFoundException{
        FakeStoreProductResponseDto fakeStoreProduct = fakeStoreClient.getSingleProduct(productId);
        if (fakeStoreProduct == null) {
            throw new ProductNotFoundException("Product not found with id = " + productId);
        }
        return fakeStoreProduct;
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
