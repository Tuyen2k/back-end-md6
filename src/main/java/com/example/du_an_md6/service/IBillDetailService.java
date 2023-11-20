package com.example.du_an_md6.service;

import com.example.du_an_md6.model.BillDetail;
import com.example.du_an_md6.model.dto.BillDetailDTO;

import java.util.List;

public interface IBillDetailService extends IGenerateService<BillDetail> {

    List<BillDetailDTO> getAddBillDetailByAccount(Long id_account);

}
