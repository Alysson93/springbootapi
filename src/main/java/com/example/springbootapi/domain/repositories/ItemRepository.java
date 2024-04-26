package com.example.springbootapi.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springbootapi.domain.entities.Item;

public interface ItemRepository extends JpaRepository<Item, Integer> {
    
}
