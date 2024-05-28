package com.example.demo.mapper;

import com.example.demo.dto.CategoryResponseDTO;
import com.example.demo.dto.CreateCategoryRequestDTO;
import com.example.demo.dto.ProductResponseDTO;
import com.example.demo.entity.Category;
import com.example.demo.entity.Product;

import java.util.ArrayList;
import java.util.List;

public class CategoryEntityDTOMapper {
    public static CategoryResponseDTO convertCategoryToCategoryResponseDTO(Category category) {
        // call product entity to product response dto mapper.
        CategoryResponseDTO categoryResponseDTO = new CategoryResponseDTO();
        categoryResponseDTO.setCategoryName(category.getName());
        List<ProductResponseDTO> productResponseDTOs = new ArrayList<>();
        if(!(category.getProducts() == null || category.getProducts().isEmpty())) {
            for (Product product : category.getProducts()) {
                productResponseDTOs.add(ProductEntityDTOMapper.convertProductEntityToProductResponseDTO(product));
            }
        }
        categoryResponseDTO.setProducts(productResponseDTOs);
        return categoryResponseDTO;
    }
    public static Category convertCreateCategoryDTOToCategory(CreateCategoryRequestDTO createCategoryRequestDTO){
        Category category = new Category();
        category.setName(createCategoryRequestDTO.getCategoryName());
        return category;
    }
}
