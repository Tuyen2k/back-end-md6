package com.example.du_an_md6.mapper;

import com.example.du_an_md6.model.Cart;
import com.example.du_an_md6.model.dto.CartDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CartMapper implements EntityMapper<CartDTO, Cart> {
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Cart toEntity(CartDTO cartDTO) {
        return modelMapper.map(cartDTO, Cart.class);
    }

    @Override
    public CartDTO toDto(Cart cart) {
        return modelMapper.map(cart, CartDTO.class);
    }

    @Override
    public List<CartDTO> toListDto(List<Cart> carts) {
        List<CartDTO> cartDTOS = new ArrayList<>();
        for(Cart cart : carts){
            CartDTO cartDTO = modelMapper.map(cart, CartDTO.class);
            cartDTOS.add(cartDTO);
        }
        return cartDTOS;
    }
}
