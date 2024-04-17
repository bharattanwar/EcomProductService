package com.example.demo.client;

import com.example.demo.dtos.FakeStoreCartResponseDto;
import com.example.demo.dtos.FakeStoreProductResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class FakeStoreClient {
    @Autowired
    private RestTemplateBuilder restTemplateBuilder;
    @Value("${fakestore.api.base.url}")  // It will fetch value from application.properties and inject that value to this variable.
    private String fakeStoreAPIBaseUrl;
    @Value("${fakestore.api.product.path}")
    private String getFakeStoreAPIProductPath;
    @Value("${fakestore.api.cart.for.user.path}")
    private String getFakeStoreAPICartForUserPath;

    // to do serialisation and deserialisation of obj to json and json to obj.
    public List<FakeStoreProductResponseDto> getAllproducts(){
        String fakeStoreGetAllProductURL = fakeStoreAPIBaseUrl.concat(getFakeStoreAPIProductPath);
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductResponseDto[]> productResponseList =
                    restTemplate.getForEntity(fakeStoreGetAllProductURL, FakeStoreProductResponseDto[].class);
        return List.of(productResponseList.getBody());

    }
    public FakeStoreProductResponseDto getSingleProduct(Long id){
        String fakeStoreGetProductURL = fakeStoreAPIBaseUrl.concat(getFakeStoreAPIProductPath).concat("/" + id);
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductResponseDto> productResponse =
                    restTemplate.getForEntity(fakeStoreGetProductURL, FakeStoreProductResponseDto.class);
        return productResponse.getBody();
    }
    public List<FakeStoreCartResponseDto> getCartByUserId(int userId){
        if(userId < 1){
            return null;
        }
        String fakeStoreGetCartForUserURL = fakeStoreAPIBaseUrl.concat(getFakeStoreAPICartForUserPath).concat(String.valueOf(userId));
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreCartResponseDto[]> cartResponse =
                restTemplate.getForEntity(fakeStoreGetCartForUserURL, FakeStoreCartResponseDto[].class);
        return List.of(cartResponse.getBody());
    }
    /* https://fakestoreapi.com/carts?userId=1 */
}

