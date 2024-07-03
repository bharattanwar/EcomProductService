package com.example.demo.InheritanceDemo.TablePerClass;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "c_tpc_instructor")
public class Instructor extends User{
    private String Company;
}
