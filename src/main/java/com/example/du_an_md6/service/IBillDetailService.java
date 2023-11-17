package com.example.du_an_md6.service;
import com.example.du_an_md6.model.BillDetail;

import java.util.List;

public interface IBillDetailService extends IGenerateService<BillDetail> {
    List<BillDetail> findAllOrders(Long id_merchant);
}
