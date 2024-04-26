package com.example.springbootapi.rest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.springbootapi.domain.entities.Cart;
import com.example.springbootapi.rest.dtos.CartRequestDTO;
import com.example.springbootapi.services.interfaces.ICartService;

@RestController
@RequestMapping("/api/v1/carts")
public class CartController {
    
    @Autowired
    private ICartService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Integer post(@RequestBody CartRequestDTO dto) {
        Cart cart = service.save(dto);
        return cart.getId();
    }

}
