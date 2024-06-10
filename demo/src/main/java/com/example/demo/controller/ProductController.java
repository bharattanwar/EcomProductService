package com.example.demo.controller;

import com.example.demo.dto.ProductResponseDTO;
import com.example.demo.entity.Category;
import com.example.demo.entity.Product;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/products")
@RestController
public class ProductController {
    @Autowired
    ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{userId}/{productId}")
    public Product getProductDetails(@PathVariable Long userId,@PathVariable Long productId){
        return productService.getProductDetails(userId,productId);
    }


    @GetMapping("")
    public ResponseEntity<List<Product>> getProducts(){
        try{
            List<Product>products=new ArrayList<>();
            products= productService.getProducts();
            return new ResponseEntity<>(products, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProducts(@PathVariable(value="id") long ids){
        try{
            if(ids<1) {
                throw new IllegalArgumentException("Product Id is <1");
            }
            Product product= productService.getProduct(ids);
            return new ResponseEntity<>(product, HttpStatus.OK);
        }catch (Exception e){
            //  return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            throw e;
        }
    }

    @PostMapping("")
    public Product createProduct(@RequestBody ProductResponseDTO productDTO){
        Product product=getProductFromDto((productDTO));
        return productService.createProduct(product);
    }
    //Partial Changes -> patchMapping
    //Replacing Complete Object ->putMapping
    @PatchMapping("{id}")
    public Product updateProduct(@PathVariable Long id,@RequestBody ProductResponseDTO productDTO){
        Product product=getProductFromDto(productDTO);
        return productService.updateProduct(id,product);
    }

    @PutMapping("{id}")
    public ResponseEntity<String> putProduct(@PathVariable Long id,@RequestBody ProductResponseDTO productDTO){
        Product product=getProductFromDto(productDTO);
        productService.putProduct(id,product);

        return new ResponseEntity<String>("updated Successfully ",HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String>deleteProduct(@PathVariable Long id){
        String message=productService.deleteProduct(id);
        return new ResponseEntity<String>(message,HttpStatus.OK);
    }

    //Product(Internal Implementation cannot be exposed & widely accepted) is sent to service layer by controller
    //so the productDTo(sent by External User) is converted to Product in the below method
    private Product getProductFromDto(ProductResponseDTO productDTO){//productDtO -> received from User
        Product product = new Product();
        product.setId(productDTO.getProductId());
        product.setTitle(productDTO.getTitle());
        product.setPrice(productDTO.getPrice());
        product.setDescription(productDTO.getDescription());
        product.setImage(productDTO.getImageURL());
        Category category=new Category();
        category.setName(productDTO.getCategory());
        product.setCategory(category);

        return product;
    }
}

