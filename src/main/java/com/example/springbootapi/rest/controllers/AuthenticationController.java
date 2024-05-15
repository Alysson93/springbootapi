package com.example.springbootapi.rest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.springbootapi.domain.entities.User;
import com.example.springbootapi.domain.repositories.UserRepository;
import com.example.springbootapi.rest.dtos.AuthenticationDTO;
import com.example.springbootapi.rest.dtos.LoginResponseDTO;
import com.example.springbootapi.rest.dtos.RegisterDTO;
import com.example.springbootapi.services.implementations.TokenService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private UserRepository repository;

    @Autowired
    private TokenService tokenService;
    
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO dto) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(dto.getLogin(), dto.getPassword());
        var auth = this.manager.authenticate(usernamePassword);
        var token = tokenService.generateToken((User) auth.getPrincipal());
        return ResponseEntity.ok(token);
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public Integer register(@RequestBody RegisterDTO dto) {
        if (this.repository.findByLogin(dto.getLogin()) != null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User already exists.");
        String encryptPasswotd = new BCryptPasswordEncoder().encode(dto.getPassword());
        User user = new User(dto.getLogin(), encryptPasswotd, dto.getRole());
        repository.save(user);
        return user.getId();
    }

}
