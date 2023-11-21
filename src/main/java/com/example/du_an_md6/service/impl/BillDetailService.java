package com.example.du_an_md6.service.impl;

import com.example.du_an_md6.mapper.BillDetailMapper;
import com.example.du_an_md6.model.Bill;
import com.example.du_an_md6.model.BillDetail;
import com.example.du_an_md6.model.dto.BillDetailDTO;
import com.example.du_an_md6.model.Merchant;
import com.example.du_an_md6.repository.IBillDetailRepository;
import com.example.du_an_md6.service.IBillDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

@Service
public class BillDetailService implements IBillDetailService {
    @Autowired
    private IBillDetailRepository iBillDetailRepository;
    @Autowired
    private BillDetailMapper billDetailMapper;

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
    public List<BillDetailDTO> getAddBillDetailByAccount(Long id_account) {
        return billDetailMapper.toListDto(iBillDetailRepository.getAddBillDetailByAccount(id_account));
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
    public List<BillDetail> statisticsByProduct(Long id_product) {
        return iBillDetailRepository.statisticsByProduct(id_product);
    }

    @Override
    public List<BillDetail> statisticsByStatus(Long id_merchant, Long id_status) {
        return iBillDetailRepository.statisticsByStatus(id_merchant,id_status);
    }

    @Override
    public List<BillDetail> statisticsByUser(Long id_merchant, Long id_user) {
        return iBillDetailRepository.statisticsByUser(id_merchant,id_user);
    }

    @Override
    public List<BillDetail> findByTimeRange(Long id_merchant,LocalDateTime startDate, LocalDateTime endDate) {
        return iBillDetailRepository.findByTimeRange(id_merchant,startDate, endDate);
    }

    @Override
    public List<BillDetail> findByYearAndWeekAndMerchant(Integer year, Integer week, Long idMerchant) {
        return iBillDetailRepository.findByYearAndWeekAndMerchant(year,week,idMerchant);
    }

    @Override
    public List<BillDetail> findAllOrders(Long id_merchant) {
        return iBillDetailRepository.findAllOrders(id_merchant);
    }

    @Override
    public List<BillDetail> revenueByStartAndEndDay(Long id_merchant, LocalDateTime start, LocalDateTime end) {
        return iBillDetailRepository.revenueByStartAndEndDay(id_merchant, start, end);
    }

    @Override
    public List<BillDetail> findByMonthAndMerchant(Integer year, Integer month, Long idMerchant) {
        return iBillDetailRepository.findByMonthAndMerchant(year, month, idMerchant);
    }


}
