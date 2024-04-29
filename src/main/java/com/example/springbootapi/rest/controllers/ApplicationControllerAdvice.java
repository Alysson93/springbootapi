package com.example.springbootapi.rest.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.springbootapi.exceptions.RegraNegocioException;

@RestControllerAdvice
public class ApplicationControllerAdvice {
    
    @ExceptionHandler(RegraNegocioException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrors handleRegraNegocioException(RegraNegocioException exception) {
        String message = exception.getMessage();
        return new ApiErrors(message);
    }

}
