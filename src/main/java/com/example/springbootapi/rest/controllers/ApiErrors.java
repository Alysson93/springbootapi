package com.example.springbootapi.rest.controllers;

import java.util.Arrays;
import java.util.List;

import lombok.Getter;

public class ApiErrors {
    
    @Getter
    private List<String> errors;

    public ApiErrors(String message) {
        this.errors = Arrays.asList(message);
    }

    public ApiErrors(List<String> messages) {
        this.errors = messages;
    }

}
