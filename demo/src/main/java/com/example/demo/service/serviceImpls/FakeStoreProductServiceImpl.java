package com.example.demo.service.serviceImpls;

import com.example.demo.client.FakeStoreClient;
import com.example.demo.client.FakeStoreDTOs.FakeStoreProductResponseDTO;
import com.example.demo.entity.Category;
import com.example.demo.entity.Product;
import com.example.demo.service.ProductService;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.lang.Nullable;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClientException;

import java.util.ArrayList;
import java.util.List;

@Service
@Primary
public class FakeStoreProductServiceImpl implements ProductService {
    private RestTemplateBuilder restTemplateBuilder;
    private FakeStoreClient fakeStoreClient;
    private RedisTemplate<String,Object>redisTemplate;

    public FakeStoreProductServiceImpl(RestTemplateBuilder restTemplateBuilder,FakeStoreClient fakeStoreApiClient,RedisTemplate<String,Object>redisTemplate) {
        this.restTemplateBuilder = restTemplateBuilder;
        this.fakeStoreClient=fakeStoreApiClient;
        this.redisTemplate=redisTemplate;
    }

    @Override
    public Product getProductDetails(Long userId, Long productId) {
        return null;
    }

    @Override
    public List<Product> getProducts() {//get all products
        RestTemplate restTemplate=restTemplateBuilder.build();
        FakeStoreProductResponseDTO[] fakeStoreProductDTOS=restTemplate.getForEntity("https://fakestoreapi.com/products", FakeStoreProductResponseDTO[].class).getBody();
        //conversion logic of ProductDto to Product
        List<Product>products=new ArrayList<>();
        for(FakeStoreProductResponseDTO prdto:fakeStoreProductDTOS){
            products.add(getProduct(prdto));
        }
        return products;
    }

    @Override
    public void putProduct(Long id, Product product) {
        RestTemplate restTemplate=new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Product> requestEntity=new HttpEntity<>(product,httpHeaders);
        restTemplate.exchange("https://fakestoreapi.com/products/{id}", HttpMethod.PUT,requestEntity,Product.class,id);

        /* RestTemplate restTemplate=restTemplateBuilder.build();
        restTemplate.put("https://fakestoreapi.com/products/{id}",Product.class,id);
        */
    }


    @Override
    public Product getProduct(long productID) {//get the particular/Specific details of the product with id
        // RestTemplate restTemplate=restTemplateBuilder.build();
        // FakeStoreProductDTO fakeStoreProductDTO=restTemplate.getForEntity("https://fakestoreapi.com/products/{id}", FakeStoreProductDTO.class,productID).getBody();


        //check if the product is in Cache
        // if yes
        //Read the product from cache
        //else
        //call FakeStore and Get Result
        //Store result in Cache


        FakeStoreProductResponseDTO fakeStoreProductDTO = null;
        fakeStoreProductDTO=(FakeStoreProductResponseDTO) redisTemplate.opsForHash().get("PRODUCTS",productID);
        if(fakeStoreProductDTO!=null){
            System.out.println("Read from Redis Cache");
            return getProduct(fakeStoreProductDTO);
        }
        fakeStoreProductDTO=fakeStoreClient.getProduct(productID);
        System.out.println("Read From FakeStoreAPI");
        redisTemplate.opsForHash().put("PRODUCTS",productID,fakeStoreProductDTO);
        return getProduct(fakeStoreProductDTO);
    }

    @Override
    public String deleteProduct(Long id) {
        return null;
    }


    @Override
    public Product createProduct(Product product) {//create / add the Product
        //  RestTemplate restTemplate=restTemplateBuilder.build();
        //  FakeStoreProductDTO fakeStoreProductDTO=restTemplate.postForEntity("https://fakestoreapi.com/products",product,FakeStoreProductDTO.class).getBody();
        FakeStoreProductResponseDTO fakeStoreProductDTO = getFakeStoreProductDTOFromProduct(product);
        return getProduct(fakeStoreClient.createProduct(fakeStoreProductDTO));
    }
    //Own Implementation of RestAPI
    public <T> ResponseEntity<T> patchForEntity(HttpMethod httpMethod,String url, @Nullable Object request, Class<T> responseType, Object... uriVariables) throws RestClientException {
        RestTemplate restTemplate = restTemplateBuilder.build();
        RequestCallback requestCallback = restTemplate.httpEntityCallback(request, responseType);
        ResponseExtractor<ResponseEntity<T>> responseExtractor = restTemplate.responseEntityExtractor(responseType);
        return restTemplate.execute(url, httpMethod, requestCallback, responseExtractor, uriVariables);
    }

    @Override
    public Product updateProduct(Long id, Product product) {//update a Product
        RestTemplate restTemplate=restTemplateBuilder.build();
        //FakeStoreProductDTO fakeStoreProductDTO=restTemplate.patchForObject("https://fakestoreapi.com/products/{id}", product , FakeStoreProductDTO.class,id);
        FakeStoreProductResponseDTO fakeStoreProductDTO2=getFakeStoreProductDTOFromProduct(product);
        ResponseEntity<FakeStoreProductResponseDTO> fakeStoreProductDTO=patchForEntity(HttpMethod.PATCH, "https://fakestoreapi.com/products/{id}" ,fakeStoreProductDTO2,FakeStoreProductResponseDTO.class,id);

        Product resultantProduct=getProduct(fakeStoreProductDTO.getBody());
        return resultantProduct;
    }


    //Alternative of PatchForEntity if some exception comes up in Patch Method
    private <T> ResponseEntity<T> requestForEntity(HttpMethod httpMethod, String url, @Nullable Object request,
                                                   Class<T> responseType, Object... uriVariables) throws RestClientException {
        RestTemplate restTemplate = restTemplateBuilder.requestFactory(
                HttpComponentsClientHttpRequestFactory.class
        ).build();
        RequestCallback requestCallback = restTemplate.httpEntityCallback(request, responseType);
        ResponseExtractor<ResponseEntity<T>> responseExtractor = restTemplate.responseEntityExtractor(responseType);
        return restTemplate.execute(url, httpMethod, requestCallback, responseExtractor, uriVariables);
    }

    private Product getProduct(FakeStoreProductResponseDTO productDTO){
        Product product = new Product();
        product.setId(productDTO.getId());
        product.setTitle(productDTO.getTitle());
        product.setPrice(productDTO.getPrice());
        product.setDescription(productDTO.getDescription());
        product.setImage(productDTO.getImage());
        Category category=new Category();
        category.setName(productDTO.getCategory());
        product.setCategory(category);

        return product;
    }

    private FakeStoreProductResponseDTO getFakeStoreProductDTOFromProduct(Product product){
        FakeStoreProductResponseDTO fakeStoreProductDTO = new FakeStoreProductResponseDTO();
        fakeStoreProductDTO.setId(product.getId());
        fakeStoreProductDTO.setTitle(product.getTitle());
        fakeStoreProductDTO.setPrice(product.getPrice());
        fakeStoreProductDTO.setImage(product.getImage());
        fakeStoreProductDTO.setDescription(product.getDescription());
        fakeStoreProductDTO.setCategory(product.getCategory().getName());
        return fakeStoreProductDTO;
    }}