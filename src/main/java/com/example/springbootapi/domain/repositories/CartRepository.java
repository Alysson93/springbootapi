package com.example.springbootapi.domain.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springbootapi.domain.entities.Cart;
import com.example.springbootapi.domain.entities.Client;

public interface CartRepository extends JpaRepository<Cart, Integer> {
    
    List<Cart> findByClient(Client client);

}
