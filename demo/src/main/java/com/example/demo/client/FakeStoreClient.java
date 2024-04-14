package com.example.demo.client;

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

    // to do serialisation and deserialisation of obj to json and json to obj.
    public List<FakeStoreProductResponseDto> getAllproducts(){
        String fakeStoreGetAllProductURL = fakeStoreAPIBaseUrl.concat(getFakeStoreAPIProductPath);
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductResponseDto[]> productResponseList =
                    restTemplate.getForEntity(fakeStoreGetAllProductURL, FakeStoreProductResponseDto[].class);
        return List.of(productResponseList.getBody());

    }
}

