package com.example.demo.service;

import com.example.demo.dto.CategoryResponseDTO;
import com.example.demo.dto.CreateCategoryRequestDTO;
import com.example.demo.entity.Category;

import java.util.List;
import java.util.UUID;

public interface CategoryService {
    CategoryResponseDTO getCategory(UUID categoryId);
    List<CategoryResponseDTO> getAllCategories();
    CategoryResponseDTO createCategory(CreateCategoryRequestDTO categoryRequestDTO);
    CategoryResponseDTO updateCategory(CreateCategoryRequestDTO createCategoryRequestDTO,UUID categoryId);
    boolean deleteCategory(UUID categoryId);
    double getTotalPriceForCategory(UUID categoryId);
}
