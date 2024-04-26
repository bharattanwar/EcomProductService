package com.example.demo.controller;

import com.example.demo.dto.CategoryResponseDTO;
import com.example.demo.dto.CreateCategoryRequestDTO;
import com.example.demo.dto.CreateProductRequestDTO;
import com.example.demo.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping()
    public ResponseEntity<List<CategoryResponseDTO>> getAllCategories(){

    }

    @GetMapping("/{id}")
    public  ResponseEntity<CategoryResponseDTO> getCategoryById(@PathVariable("id")UUID categoryId){

    }

    @PostMapping
    public ResponseEntity<CategoryResponseDTO> createCategory(@RequestBody CreateCategoryRequestDTO createCategoryRequestDTO){

    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponseDTO> updateCategory(@PathVariable("id")UUID categoryId, @RequestBody CreateProductRequestDTO createProductRequestDTO){

    }

    @DeleteMapping("/{id}")
    public RequestEntity<Boolean> deleteCategory(@PathVariable("id")UUID categoryId){

    }

}
