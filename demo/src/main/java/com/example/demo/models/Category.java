package com.example.demo.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class Category extends BaseModel{
    private String name;
    private String description;
    private Set<Product> products;
}
