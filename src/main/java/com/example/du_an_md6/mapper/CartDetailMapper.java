package com.example.du_an_md6.mapper;

import com.example.du_an_md6.model.Cart;
import com.example.du_an_md6.model.CartDetail;
import com.example.du_an_md6.model.dto.CartDTO;
import com.example.du_an_md6.model.dto.CartDetailDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CartDetailMapper implements EntityMapper<CartDetailDTO, CartDetail> {
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CartDetail toEntity(CartDetailDTO cartDetailDTO) {
        return modelMapper.map(cartDetailDTO, CartDetail.class);
    }

    @Override
    public CartDetailDTO toDto(CartDetail cartDetail) {
        return modelMapper.map(cartDetail, CartDetailDTO.class);
    }

    @Override
    public List<CartDetailDTO> toListDto(List<CartDetail> cartDetails) {
        List<CartDetailDTO> cartDetailDTOS = new ArrayList<>();
        for(CartDetail cartDetail : cartDetails){
            CartDetailDTO cartDTO = modelMapper.map(cartDetail, CartDetailDTO.class);
            cartDetailDTOS.add(cartDTO);
        }
        return cartDetailDTOS;
    }
}
