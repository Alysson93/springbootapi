package com.example.springbootapi.rest.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthenticationDTO {
    
    private String login;
    private String password;

}
