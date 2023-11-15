package com.example.du_an_md6.service.impl;

import com.example.du_an_md6.mapper.AccountMapper;
import com.example.du_an_md6.mapper.CartDetailMapper;
import com.example.du_an_md6.model.CartDetail;
import com.example.du_an_md6.model.dto.CartDetailDTO;
import com.example.du_an_md6.repository.ICartDetailRepository;
import com.example.du_an_md6.service.ICartDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartDetailService implements ICartDetailService {
    @Autowired
    private ICartDetailRepository iCartDetailRepository;
    @Autowired
    private CartDetailMapper cartDetailMapper;
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
    public void deleteCartDetail(Long id_cart_detail) {
        iCartDetailRepository.deleteById(id_cart_detail);
    }

    @Override
    public List<CartDetailDTO> getAllCartDetailByAccount(Long id_account, Long id_status) {
        return cartDetailMapper.toListDto(iCartDetailRepository.getCartDetailByAccount(id_account, id_status));
    }

    @Override
    public CartDetail getCartDetailByCartAndProduct(Long id_cart, Long id_product) {
        return iCartDetailRepository.getCartDetailByCartAndProduct(id_cart, id_product);
    }
}
