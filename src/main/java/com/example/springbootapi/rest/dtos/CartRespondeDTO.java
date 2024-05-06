package com.example.springbootapi.rest.dtos;

import java.math.BigDecimal;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartRespondeDTO {
    
    private Integer id;
    private String cpf;
    private String clientName;
    private BigDecimal total;
    private String createdAt;
    private String status;
    private List<ItemResponseDTO> items;

}
