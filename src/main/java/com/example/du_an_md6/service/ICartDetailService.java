package com.example.du_an_md6.service;

import com.example.du_an_md6.model.CartDetail;
import com.example.du_an_md6.model.dto.CartDetailDTO;

import java.util.List;

public interface ICartDetailService extends IGenerateService<CartDetail>{

    List<CartDetailDTO> getAllCartDetailByCart(Long id_cart);

}
