package com.example.springbootapi.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.springbootapi.domain.entities.Client;


public interface ClientRepository extends JpaRepository<Client, Integer> {

    @Query("select c from Client c left join fetch c.carts where c.id = :id")
    Client findClientFetchCarts(@Param("id") Integer id);

}
