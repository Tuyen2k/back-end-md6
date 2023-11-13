package com.example.du_an_md6.service;

import com.example.du_an_md6.model.CartDetail;

import java.util.List;

public interface ICartDetailService extends IGenerateService<CartDetail>{

    List<CartDetail> getAllCartDetailByCart(Long id_cart);

}
