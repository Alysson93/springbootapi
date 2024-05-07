package com.example.springbootapi.rest.dtos;

import java.math.BigDecimal;
import java.util.List;

import com.example.springbootapi.validations.NotEmptyList;

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

    @NotEmptyList(message = "Pedido não pode ser realizado sem itens.")
    private List<ItemRequestDTO> items;

}
