package com.example.demo.controller;

import com.example.demo.client.FakeStoreDTOs.SearchRequestDTO;
import com.example.demo.dto.ProductResponseDTO;
import com.example.demo.entity.Product;
import com.example.demo.service.SearchProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/search")
@RestController
public class SearchController {

    private SearchProductService searchProductService;

    public SearchController(SearchProductService searchProductService) {
        this.searchProductService = searchProductService;
    }

    @PostMapping("/product")
    public Page<Product> searchProduct(@RequestBody SearchRequestDTO searchRequestDTO) {
//        List<Product> products=searchProductService.searchProduct(searchRequestDTO.getQuery(),
//                searchRequestDTO.getPageSize(),searchRequestDTO.getPageNumber());
//        List<ProductDTO>productDTOS=new ArrayList<>();
//        for(Product product:products){
//            productDTOS.add(getProductDTOFromProduct(product));
//        }
        return searchProductService.searchProduct(searchRequestDTO.getQuery(), searchRequestDTO.getPageNumber(), searchRequestDTO.getPageSize(), searchRequestDTO.getSortParamList());
        //return new ResponseEntity<>(product, HttpStatus.OK);
    }

    ProductResponseDTO getProductDTOFromProduct(Product product) {
        ProductResponseDTO productDTO = new ProductResponseDTO();
        productDTO.setProductId(product.getId());
        productDTO.setPrice(product.getPrice());
        productDTO.setTitle(product.getTitle());
        productDTO.setImageURL(product.getImage());
        productDTO.setCategory(product.getCategory().getName());
        productDTO.setDescription(product.getDescription());
        return productDTO;
    }
}

