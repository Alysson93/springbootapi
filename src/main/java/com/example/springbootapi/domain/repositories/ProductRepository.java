package com.example.springbootapi.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springbootapi.domain.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    
}
