package com.example.springbootapi.services.interfaces;

import java.util.Optional;

import com.example.springbootapi.domain.entities.Cart;
import com.example.springbootapi.rest.dtos.CartRequestDTO;

public interface ICartService {
 
    Cart save(CartRequestDTO dto);

    Optional<Cart> getCart(Integer id);

}
