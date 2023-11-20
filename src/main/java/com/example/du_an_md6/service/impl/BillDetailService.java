package com.example.du_an_md6.service.impl;

import com.example.du_an_md6.model.Bill;
import com.example.du_an_md6.model.BillDetail;
import com.example.du_an_md6.model.Merchant;
import com.example.du_an_md6.repository.IBillDetailRepository;
import com.example.du_an_md6.service.IBillDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillDetailService implements IBillDetailService {
    @Autowired
    private IBillDetailRepository iBillDetailRepository;

    @Override
    public List<BillDetail> findAll() {
        return iBillDetailRepository.findAll();
    }

    @Override
    public BillDetail findById(Long id) {
        return iBillDetailRepository.findById(id).orElse(null);
    }

    @Override
    public void save(BillDetail billDetail) {
        iBillDetailRepository.save(billDetail);
    }

    @Override
    public List<BillDetail> findByBill_Account_NameContainingAndBill_Merchant(String name, Merchant Merchant) {
        return iBillDetailRepository.findByBill_Account_NameContainingAndBill_Merchant(name,Merchant);
    }

    @Override
    public List<BillDetail> findByBill_Account_PhoneContainingAndBill_Merchant(String name, Merchant Merchant) {
        return iBillDetailRepository.findByBill_Account_PhoneContainingAndBill_Merchant(name,Merchant);

    }

    @Override
    public List<BillDetail> findAllOrders(Long id_merchant) {
        return iBillDetailRepository.findAllOrders(id_merchant);
    }
}
