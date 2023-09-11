package com.example.warsztatsamochodowy.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "roles")
public class Role {

    public static final String ROLE_ADMIN = "ROLE_ADMIN";
    public static final String ROLE_WORKER = "ROLE_WORKER";
    public static final String ROLE_USER = "ROLE_USER";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
