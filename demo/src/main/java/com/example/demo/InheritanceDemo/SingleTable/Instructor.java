package com.example.demo.InheritanceDemo.SingleTable;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "c_st_instructor")
@DiscriminatorValue(value = "3")
public class Instructor extends User {
    private String Company;
}