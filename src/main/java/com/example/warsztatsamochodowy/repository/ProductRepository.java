package com.example.warsztatsamochodowy.repository;

import com.example.warsztatsamochodowy.entity.Product;
import com.example.warsztatsamochodowy.entity.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
