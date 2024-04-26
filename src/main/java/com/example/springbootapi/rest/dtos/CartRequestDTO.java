package com.example.springbootapi.rest.dtos;

import java.math.BigDecimal;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartRequestDTO {
    
    private Integer clientId;
    private BigDecimal total;
    private List<ItemRequestDTO> items;

}
