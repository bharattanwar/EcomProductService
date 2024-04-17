package com.example.demo.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class FakeStoreCartResponseDto {
    private int id;
    private int userId;
    private String date;
    private List<ProductQuantityDto> products;
    private int __v;
}
