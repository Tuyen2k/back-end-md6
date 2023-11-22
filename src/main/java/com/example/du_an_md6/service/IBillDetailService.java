package com.example.du_an_md6.service;

import com.example.du_an_md6.model.BillDetail;
import com.example.du_an_md6.model.Merchant;
import com.example.du_an_md6.model.dto.BillDetailDTO;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface IBillDetailService extends IGenerateService<BillDetail> {

    List<BillDetailDTO> getAddBillDetailByAccount(Long id_account);


}
