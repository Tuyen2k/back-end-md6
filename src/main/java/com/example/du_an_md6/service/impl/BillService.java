package com.example.du_an_md6.service.impl;

import com.example.du_an_md6.model.Bill;
import com.example.du_an_md6.repository.IBillRepository;
import com.example.du_an_md6.service.IBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

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
    public Bill findByAccountAndMerchant(Long id_account, Long id_merchant, LocalDateTime time) {
        return iBillRepository.findByAccountAndMerchant(id_account, id_merchant, time).orElse(null);
    }
}
