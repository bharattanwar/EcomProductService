package com.example.demo.InheritanceDemo.JoinedClass;

import jakarta.persistence.*;

@Entity(name = "c_jc_user")
@Inheritance(strategy = InheritanceType.JOINED)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private String email;
}