package com.example.demo.client;

import com.example.demo.client.FakeStoreDTOs.FakeStoreProductResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class FakeStoreClient {
    private RestTemplateBuilder restTemplateBuilder;

    public FakeStoreClient(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
    }

    public FakeStoreProductResponseDTO getProduct(long productID) {//get the particular/Specific details of the product with id
        RestTemplate restTemplate=restTemplateBuilder.build();
        FakeStoreProductResponseDTO fakeStoreProductDTO=restTemplate.getForEntity("https://fakestoreapi.com/products/{id}", FakeStoreProductResponseDTO.class,productID).getBody();
        //conversion logic of ProductDto to Product
        return fakeStoreProductDTO;
    }

   /* public void deleteProduct(Long productId){
        RestTemplate restTemplate=restTemplateBuilder.build();
        restTemplate.delete("https://fakestoreapi.com/products/{id}");
    }*/

    public FakeStoreProductResponseDTO createProduct(FakeStoreProductResponseDTO fakeStoreProductDTO) {//create / add the Product
        RestTemplate restTemplate=restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductResponseDTO> fakeStoreProductDTOResponseEntity=restTemplate.postForEntity("https://fakestoreapi.com/products",fakeStoreProductDTO,FakeStoreProductResponseDTO.class);
        return fakeStoreProductDTOResponseEntity.getBody();
    }

    /*public FakeStoreProductDTO updateProduct(FakeStoreProductDTO fakeStoreProductDTO){
        RestTemplate restTemplate=restTemplateBuilder.build();
        FakeStoreProductDTO fakeStoreProductDTO1=restTemplate.patchForObject("https://fakestoreapi.com/products/{id}",fakeStoreProductDTO,FakeStoreProductDTO.class);
        return fakeStoreProductDTO1;
    }*/

}