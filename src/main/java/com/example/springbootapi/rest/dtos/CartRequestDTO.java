package com.example.springbootapi.rest.dtos;

import java.math.BigDecimal;
import java.util.List;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartRequestDTO {
    
    @NotNull(message = "Informe o ID do cliente")
    private Integer clientId;

    @NotNull(message = "Campo total do pedido é obrigatório.")
    private BigDecimal total;

    private List<ItemRequestDTO> items;

}
