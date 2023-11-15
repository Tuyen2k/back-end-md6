package com.example.du_an_md6.service;

import com.example.du_an_md6.model.Coupon;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ICouponService extends IGenerateService<Coupon>{

    List<Coupon> findAllByIdMerchant(Long id_merchant);
    void delete(Long id_coupon);
}
