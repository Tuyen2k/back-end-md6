package com.example.du_an_md6.service;

import com.example.du_an_md6.model.Bill;
import com.example.du_an_md6.model.Coupon;

import java.time.LocalDateTime;
import java.util.List;


public interface IBillService extends IGenerateService<Bill> {
    Bill findByAccountAndMerchant(Long id_account, Long id_merchant);
    Bill findByAccountAndMerchantAndCode(Long id_account, Long id_merchant, String codePurchase);

    List<Bill> getAllBillyMerchant(Long id_merchant);
    void handleDiscount(List<Bill> bill, List<Coupon> coupons);
}
