package com.example.du_an_md6.service;

import com.example.du_an_md6.model.Bill;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDateTime;


public interface IBillService extends IGenerateService<Bill> {
    Bill findByAccountAndMerchant(Long id_account, Long id_merchant);
    Bill findByAccountAndMerchantAndCode(Long id_account, Long id_merchant, String codePurchase);

}
