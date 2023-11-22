package com.example.du_an_md6.service.impl;

import com.example.du_an_md6.mapper.BillDetailMapper;
import com.example.du_an_md6.model.BillDetail;
import com.example.du_an_md6.model.Merchant;
import com.example.du_an_md6.model.dto.BillDetailDTO;
import com.example.du_an_md6.repository.IBillDetailRepository;
import com.example.du_an_md6.service.IBillDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.WeekFields;
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

}
