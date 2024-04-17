package com.example.demo.controllers;

import com.example.demo.client.FakeStoreClient;
import com.example.demo.dtos.FakeStoreCartResponseDto;
import com.example.demo.exceptions.CartNotFoundException;
import com.example.demo.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private FakeStoreClient fakeStoreClient;

    @GetMapping("/{userId}")
    public ResponseEntity getCartForUser(@PathVariable("userId") int userId){
        List<FakeStoreCartResponseDto> cartResponse = fakeStoreClient.getCartByUserId(userId);
        if(cartResponse == null){
            throw new CartNotFoundException("Cart do not exist for userId=" + userId);
        }
        return ResponseEntity.ok(cartResponse);
    }
}
