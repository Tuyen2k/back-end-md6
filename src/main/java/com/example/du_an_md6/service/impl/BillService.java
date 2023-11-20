package com.example.du_an_md6.service.impl;

import com.example.du_an_md6.model.Account;
import com.example.du_an_md6.model.Bill;
import com.example.du_an_md6.repository.IBillRepository;
import com.example.du_an_md6.service.IBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class BillService implements IBillService {
    @Autowired
    private IBillRepository iBillRepository;

    @Override
    public List<Bill> findAll() {
        return iBillRepository.findAll();
    }

    @Override
    public Bill findById(Long id) {
        return iBillRepository.findById(id).orElse(null);
    }

    @Override
    public void save(Bill bill) {
        iBillRepository.save(bill);
    }

    @Override
    public Bill findByAccountAndMerchant(Long id_account, Long id_merchant) {
        return iBillRepository.findByAccountAndMerchant(id_account, id_merchant).orElse(null);
    }
    @Override
    public Bill findByAccountAndMerchantAndCode(Long id_account, Long id_merchant, String codePurchase) {
        return iBillRepository.findByAccountAndMerchantAndCode(id_account, id_merchant, codePurchase).orElse(null);
    }

    @Override
    public List<Bill> getAllBillyMerchant(Long id_merchant) {
        List<Bill> bills = iBillRepository.getBillByMerchant(id_merchant);
        Set<Account> uniqueAccounts = new HashSet<>();
        List<Bill> filteredBills = new ArrayList<>();
        for (Bill bill : bills) {
            Account account = bill.getAccount();
            if (uniqueAccounts.add(account)) {
                filteredBills.add(bill);
            }
        }

        return filteredBills;
    }
}
