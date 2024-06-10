package com.example.demo.service.serviceImpls;

import com.example.demo.entity.Product;
import com.example.demo.entity.SortParam;
import com.example.demo.entity.SortType;
import com.example.demo.repository.ProductRepository;
import com.example.demo.service.SearchProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchProductServiceImpl implements SearchProductService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public Page<Product> searchProduct(String query, int pageNumber, int pageSize, List<SortParam> sortParamLists) {
        Sort sort=null;
        if(!sortParamLists.isEmpty()){
            if(sortParamLists.get(0).getSortType().equals(SortType.Asc)){
                sort=Sort.by(sortParamLists.get(0).getParamName());
            }else{
                sort=Sort.by(sortParamLists.get(0).getParamName()).descending();
            }
        }
        for(int i=1;i<sortParamLists.size();i++){
            if(sortParamLists.get(i).getSortType().equals(SortType.Asc)){
                sort=sort.and(Sort.by(sortParamLists.get(0).getParamName()));
            }else{
                sort=sort.and(Sort.by(sortParamLists.get(i).getParamName()).descending());
            }
        }

        return productRepository.findByTitleEquals(query, PageRequest.of(pageNumber,pageSize,sort));
    }

}
