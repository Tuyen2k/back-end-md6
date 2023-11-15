package com.example.du_an_md6.service;

import com.example.du_an_md6.model.CartDetail;
import com.example.du_an_md6.model.dto.CartDetailDTO;

import java.util.List;

public interface ICartDetailService extends IGenerateService<CartDetail>{
    void deleteCartDetail(Long id_cart_detail);

    List<CartDetailDTO> getAllCartDetailByAccount(Long id_account, Long id_status);
    CartDetail getCartDetailByCartAndProduct(Long id_cart, Long id_product);

}
