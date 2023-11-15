package com.example.du_an_md6.service;

import com.example.du_an_md6.model.Bill;


public interface IBillService extends IGenerateService<Bill> {
    Bill findByAccountAndMerchant(Long id_account, Long id_merchant);
}
