package com.example.demo.controllers;

import com.example.demo.dtos.FakeStoreProductResponseDto;
import com.example.demo.dtos.ProductDto;
import com.example.demo.exceptions.InvalidProductIdException;
import com.example.demo.models.Product;
import com.example.demo.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping()
    public ResponseEntity getAllProducts() {
        List<FakeStoreProductResponseDto> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity getSingleProduct(@PathVariable("id") Long id){
        FakeStoreProductResponseDto product = productService.getSingleProduct(id);
        if(id < 1){
            throw new InvalidProductIdException("Input Id is not valid");
        }
        return ResponseEntity.ok(product);
    }
}