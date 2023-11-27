package com.example.du_an_md6.service.impl;

import com.example.du_an_md6.model.Account;
import com.example.du_an_md6.model.Bill;
import com.example.du_an_md6.model.BillDetail;
import com.example.du_an_md6.model.Coupon;
import com.example.du_an_md6.repository.IBillDetailRepository;
import com.example.du_an_md6.repository.IBillRepository;
import com.example.du_an_md6.service.IBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class BillService implements IBillService {
    @Autowired
    private IBillRepository iBillRepository;
    @Autowired
    private IBillDetailRepository iBillDetailRepository;

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

    @Override
    public void handleDiscount(List<Bill> bills, List<Coupon> coupons){
        double discount = 0.0;
        for (Coupon coupon : coupons){
            for (Bill bill: bills){
                if (Objects.equals(coupon.getMerchant().getId_merchant(), bill.getMerchant().getId_merchant())){
                    if (!Objects.equals(coupon.getDiscountAmount(), null)){
                        discount = coupon.getDiscountAmount();
                    }else {
                        List<BillDetail> billDetails = iBillDetailRepository.findAllByBill(bill.getId_bill());
                        double total = 0.0;
                        for(BillDetail billDetail: billDetails){
                            total += billDetail.getPrice() * billDetail.getQuantity();
                        }
                        discount = total*coupon.getPercentageDiscount()/100;
                    }
                }
                bill.setDiscount(discount);
                iBillRepository.save(bill);
            }
        }

    }

}
