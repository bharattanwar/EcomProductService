package com.example.demo.Services;

import com.example.demo.dto.UserDTO;
import com.example.demo.entity.Product;
import com.example.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class StorageProductService implements IProductServices{
    //this class will interact with ProductRepo

    @Autowired
    private ProductRepository productRepository;

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    @Override
    public Product getProductDetails(Long userId, Long productId){
        Product product=productRepository.findById(productId).get();
        System.out.println(product.getTitle());

        RestTemplate restTemplate1=restTemplate();
        UserDTO userDTO=restTemplate1.getForEntity("http://localhost:8082/users/{id}",UserDTO.class,userId).getBody();
        if(userDTO.getEmail()==null)
            System.out.println("Null");

        System.out.println(userDTO.getEmail());
        return product;
    }


    @Override
    public List<Product> getProducts() {
        return null;
    }

    @Override
    public Product getProduct(long id) {
        return productRepository.findById(id).get();
    }

    @Override
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        return null;
    }

    @Override
    public String deleteProduct(Long id) {
        Optional<Product> optionalProduct=productRepository.findById(id);
        if(!optionalProduct.isEmpty()){
            productRepository.deleteById(id);
            return "Deleted Successfully";
        }
        return "product not present";
    }

    @Override
    public void putProduct(Long id, Product product) {
        createProduct(product);

    }
}
