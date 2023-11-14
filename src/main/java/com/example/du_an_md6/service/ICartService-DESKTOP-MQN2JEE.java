package com.example.du_an_md6.service;

import com.example.du_an_md6.model.Cart;

public interface ICartService extends IGenerateService<Cart>{
    Cart findCartByAccount(Long id_account);
    Cart findCartByAccountAndMerchantAndStatus(Long id_account, Long id_merchant, Long id_status);
}
