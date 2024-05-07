package com.example.springbootapi.rest.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.springbootapi.domain.entities.Product;
import com.example.springbootapi.domain.repositories.ProductRepository;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    
    @Autowired
    private ProductRepository repository;

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @GetMapping
    public List<Product> get(Product filter) {
        ExampleMatcher matcher = ExampleMatcher
            .matching()
            .withIgnoreCase()
            .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example example = Example.of(filter, matcher);
        List<Product> products = repository.findAll(example);
        return products;
    }

    @GetMapping("/{id}")
    public Product getBy(@PathVariable Integer id) {
        Optional<Product> product = repository.findById(id);
        if (product.isPresent()) return product.get();
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado.");
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Integer post(@RequestBody @Valid Product product) {
        repository.save(product);
        return product.getId();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        Optional<Product> product = repository.findById(id);
        if (product.isPresent()) repository.delete(product.get());
        else throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado");
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void put(@PathVariable Integer id, @RequestBody @Valid Product product) {
        Optional<Product> p = repository.findById(id);
        if (p.isPresent()) {
            product.setId(p.get().getId());
            repository.save(product);
        } else throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado");
    } 

}
