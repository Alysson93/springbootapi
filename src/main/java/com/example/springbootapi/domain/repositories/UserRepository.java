package com.example.springbootapi.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.springbootapi.domain.entities.User;


public interface UserRepository extends JpaRepository<User, Integer> {
    
    UserDetails findByLogin(String login);

}
