package com.example.springbootapi.services.implementations;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.springbootapi.domain.entities.Cart;
import com.example.springbootapi.domain.entities.Client;
import com.example.springbootapi.domain.entities.Item;
import com.example.springbootapi.domain.entities.Product;
import com.example.springbootapi.domain.repositories.CartRepository;
import com.example.springbootapi.domain.repositories.ClientRepository;
import com.example.springbootapi.domain.repositories.ItemRepository;
import com.example.springbootapi.domain.repositories.ProductRepository;
import com.example.springbootapi.exceptions.RegraNegocioException;
import com.example.springbootapi.rest.dtos.CartRequestDTO;
import com.example.springbootapi.rest.dtos.ItemRequestDTO;
import com.example.springbootapi.services.interfaces.ICartService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CartService implements ICartService {
 
    private final CartRepository repository;
    private final ClientRepository clientRepository;
    private final ProductRepository productRepository;
    private final ItemRepository itemRepository;

    @Override
    @Transactional
    public Cart save(CartRequestDTO dto) {
        Cart cart = new Cart();
        cart.setTotal(dto.getTotal());
        cart.setCreatedAt(LocalDate.now());

        Client client = clientRepository.findById(dto.getClientId())
            .orElseThrow(() -> new RegraNegocioException("Client not found"));
        cart.setClient(client);
        
        List<Item> items = convertItems(cart, dto.getItems());
        repository.save(cart);
        itemRepository.saveAll(items);
        cart.setItems(items);
        return cart;
    }

    @Override
    public Optional<Cart> getCart(Integer id) {
        return repository.findByIdFetchItems(id);
    }

    private List<Item> convertItems(Cart cart, List<ItemRequestDTO> items) {
        if (items.isEmpty()) {
            throw new RegraNegocioException("Não é possível realizar um pedido sem items.");
        }
        return items.stream()
            .map(dto -> {
                Product product = productRepository.findById(dto.getProductId())
                    .orElseThrow(() -> new RegraNegocioException("Código de produto inválido: " + dto.getProductId()));
                Item item = new Item();
                item.setQtd(dto.getQtd());
                item.setCart(cart);
                item.setProduct(product);
                return item;
            }).collect(Collectors.toList());
    }

}
