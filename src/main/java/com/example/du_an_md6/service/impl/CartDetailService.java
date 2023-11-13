package com.example.du_an_md6.service.impl;

import com.example.du_an_md6.model.CartDetail;
import com.example.du_an_md6.repository.ICartDetailRepository;
import com.example.du_an_md6.service.ICartDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartDetailService implements ICartDetailService {
    @Autowired
    private ICartDetailRepository iCartDetailRepository;
    @Override
    public List<CartDetail> findAll() {
        return iCartDetailRepository.findAll();
    }

    @Override
    public CartDetail findById(Long id) {
        return iCartDetailRepository.findById(id).orElse(null);
    }

    @Override
    public void save(CartDetail cartDetail) {
        iCartDetailRepository.save(cartDetail);
    }

    @Override
    public List<CartDetail> getAllCartDetailByCart(Long id_cart) {
        return iCartDetailRepository.getCartDetailByCart(id_cart);
    }
}
