package com.example.springbootapi.domain.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.springbootapi.domain.entities.Cart;
import com.example.springbootapi.domain.entities.Client;

public interface CartRepository extends JpaRepository<Cart, Integer> {
    
    List<Cart> findByClient(Client client);

    @Query("SELECT c FROM Cart c LEFT JOIN FETCH c.items WHERE c.id = :id")
    Optional<Cart> findByIdFetchItems(@Param("id") Integer id);

}
