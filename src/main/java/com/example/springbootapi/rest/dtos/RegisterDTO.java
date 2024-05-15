package com.example.springbootapi.rest.dtos;

import com.example.springbootapi.enums.UserRole;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterDTO {
    
    private String login;
    private String password;
    private UserRole role;

}
