package com.example.du_an_md6.service.impl;

import com.example.du_an_md6.model.Coupon;
import com.example.du_an_md6.repository.ICouponRepository;
import com.example.du_an_md6.service.ICouponService;
import com.example.du_an_md6.service.IMerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CouponService implements ICouponService {
    @Autowired
    ICouponRepository repository;
    @Autowired
    IMerchantService merchantService;
    @Override
    public List<Coupon> findAll() {
        return repository.findAll();
    }

    @Override
    public Coupon findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public void save(Coupon coupon) {
        repository.save(coupon);
    }

    @Override
    public List<Coupon> findAllByIdMerchant(Long id_merchant) {
        return repository.findCouponByMerchant(merchantService.findById(id_merchant));
    }

    @Override
    public void delete(Long id_coupon) {
        repository.deleteById(id_coupon);
    }
}
