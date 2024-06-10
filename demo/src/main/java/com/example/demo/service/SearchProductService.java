package com.example.demo.service;

import com.example.demo.entity.Product;
import com.example.demo.entity.SortParam;
import org.springframework.data.domain.Page;

import java.util.List;

public interface SearchProductService {
    Page<Product> searchProduct(String query, int pageNumber, int pageSize, List<SortParam> sortParamLists);
}
