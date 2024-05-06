package com.example.springbootapi.exceptions;

public class CartNotFoundException extends RuntimeException {
    
    public CartNotFoundException() {
        super("Cart not found");
    }

}
