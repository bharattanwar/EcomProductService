package com.example.demo.service;

import com.example.demo.client.FakeStoreClient;
import com.example.demo.dto.CategoryResponseDTO;
import com.example.demo.dto.CreateCategoryRequestDTO;
import com.example.demo.dto.FakeStoreDTOs.FakeStoreProductResponseDTO;
import com.example.demo.entity.Category;
import com.example.demo.entity.Product;
import com.example.demo.exception.CategoryNotFoundException;
import com.example.demo.mapper.CategoryEntityDTOMapper;
import com.example.demo.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service("categoryService")
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    private FakeStoreClient fakeStoreClient;

    @Override
    public CategoryResponseDTO getCategory(UUID categoryId) {
        Category category =  categoryRepository.findById(categoryId).orElseThrow(() -> new CategoryNotFoundException("Category not found"));
        return CategoryEntityDTOMapper.convertCategoryToCategoryResponseDTO(category);
    }

    @Override
    public List<CategoryResponseDTO> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        List<CategoryResponseDTO> categoryResponseDTOS = new ArrayList<>();
        for(Category c : categories){
            categoryResponseDTOS.add(CategoryEntityDTOMapper.convertCategoryToCategoryResponseDTO(c));
        }
        return  categoryResponseDTOS;
    }

    @Override
    public CategoryResponseDTO createCategory(CreateCategoryRequestDTO categoryRequestDTO) {
        Category category = CategoryEntityDTOMapper.convertCreateCategoryDTOToCategory(categoryRequestDTO);
        category = categoryRepository.save(category);
        return CategoryEntityDTOMapper.convertCategoryToCategoryResponseDTO(category);
    }

    @Override
    public CategoryResponseDTO updateCategory(CreateCategoryRequestDTO createCategoryRequestDTO, UUID categoryId) {
        return null;
    }

    @Override
    public boolean deleteCategory(UUID categoryId) {
        return false;
    }

    @Override
    public double getTotalPriceForCategory(UUID categoryId) {
        List<FakeStoreProductResponseDTO> productResponseDTOS = fakeStoreClient.getAllProducts();
        Category category = categoryRepository.findById(categoryId).orElseThrow(
                () -> new CategoryNotFoundException("Category for the given id is not found")
        );
        if(category.getProducts().isEmpty()){
            return 0;
        } else{
            double sum = 0;
            for(Product p : category.getProducts()){
                sum = sum + p.getPrice();
            }
            return sum;
        }
    }
}
