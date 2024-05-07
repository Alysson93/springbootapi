package com.example.springbootapi.rest.controllers;

import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.springbootapi.domain.entities.Cart;
import com.example.springbootapi.domain.entities.Item;
import com.example.springbootapi.enums.StatusCart;
import com.example.springbootapi.rest.dtos.CartRequestDTO;
import com.example.springbootapi.rest.dtos.CartRespondeDTO;
import com.example.springbootapi.rest.dtos.ItemResponseDTO;
import com.example.springbootapi.rest.dtos.StatusCartDTO;
import com.example.springbootapi.services.interfaces.ICartService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/carts")
public class CartController {
    
    @Autowired
    private ICartService service;

    @GetMapping("/{id}")
    public CartRespondeDTO getBy(@PathVariable Integer id) {
        return service.getCart(id)
            .map(c -> convert(c))
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cart not found"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Integer post(@RequestBody @Valid CartRequestDTO dto) {
        Cart cart = service.save(dto);
        return cart.getId();
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void patchStatus(@PathVariable Integer id, @RequestBody StatusCartDTO dto) {
        service.updateStatus(id, StatusCart.valueOf(dto.getStatus()));
    }

    private CartRespondeDTO convert(Cart cart) {
        return CartRespondeDTO.builder()
            .id(cart.getId())
            .createdAt(cart.getCreatedAt().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
            .cpf(cart.getClient().getCpf())
            .clientName(cart.getClient().getName())
            .total(cart.getTotal())
            .status(cart.getStatus().name())
            .items(convert(cart.getItems()))
            .build();
    }

    private List<ItemResponseDTO> convert(List<Item> items) {
        if (CollectionUtils.isEmpty(items)) {
            return Collections.emptyList();
        }
        return items.stream()
            .map(item -> ItemResponseDTO.builder()
                .description(item.getProduct().getDescription())
                .price(item.getProduct().getPrice())
                .qtd(item.getQtd())
                .build()
            ).collect(Collectors.toList());
    }

}
