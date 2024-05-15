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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.springbootapi.domain.entities.Client;
import com.example.springbootapi.domain.repositories.ClientRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private ClientRepository repository;

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @GetMapping
    public List<Client> get(Client filter) {
        ExampleMatcher matcher = ExampleMatcher
            .matching()
            .withIgnoreCase()
            .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example example = Example.of(filter, matcher);
        List<Client> clients = repository.findAll(example);
        return clients;
    }
    
    @GetMapping("/{id}")
    public Client getBy(@PathVariable Integer id) {
        Optional<Client> client = repository.findById(id);
        if (client.isPresent()) return client.get();
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado.");
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Integer post(@RequestBody @Valid Client client) {
        repository.save(client);
        return client.getId();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        Optional<Client> client = repository.findById(id);
        if (client.isPresent()) repository.delete(client.get());
        else throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado.");
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void put(@PathVariable Integer id, @RequestBody @Valid Client client) {
        Optional<Client> c = repository.findById(id);
        if (c.isPresent()) {
            client.setId(c.get().getId());
            repository.save(client);
        } else throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado.");
    }   

}
