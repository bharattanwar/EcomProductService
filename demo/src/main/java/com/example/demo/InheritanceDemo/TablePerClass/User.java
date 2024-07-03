package com.example.demo.InheritanceDemo.TablePerClass;

import jakarta.persistence.*;

@Entity(name = "c_tpc_user")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private String email;
}
