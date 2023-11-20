package com.example.du_an_md6.service;
import com.example.du_an_md6.model.BillDetail;
import com.example.du_an_md6.model.Merchant;

import java.util.List;

public interface IBillDetailService extends IGenerateService<BillDetail> {
    List<BillDetail> findAllOrders(Long id_merchant);
    List<BillDetail> findByBill_Account_NameContainingAndBill_Merchant(String name, Merchant Merchant);
    List<BillDetail> findByBill_Account_PhoneContainingAndBill_Merchant(String name, Merchant Merchant);
}
