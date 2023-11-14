package com.example.du_an_md6.service.impl;

import com.example.du_an_md6.model.Cart;
import com.example.du_an_md6.repository.ICartRepository;
import com.example.du_an_md6.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService implements ICartService {
    @Autowired
    private ICartRepository iCartRepository;
    @Override
    public List<Cart> findAll() {
        return iCartRepository.findAll();
    }

    @Override
    public Cart findById(Long id) {
        return iCartRepository.findById(id).orElse(null);
    }

    @Override
    public void save(Cart cart) {
        iCartRepository.save(cart);
    }

    @Override
    public Cart findCartByAccount(Long id_account) {
        return iCartRepository.findByAccount(id_account).orElse(null);
    }

    @Override
    public Cart findCartByAccountAndMerchantAndStatus(Long id_account, Long id_merchant, Long id_status) {
        return iCartRepository.findByAccountAndMerchant(id_account, id_merchant, id_status).orElse(null);
    }
}
