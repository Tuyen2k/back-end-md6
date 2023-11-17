package com.example.du_an_md6.repository;

import com.example.du_an_md6.model.Bill;
import com.example.du_an_md6.model.BillDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IBillDetailRepository extends JpaRepository<BillDetail, Long> {
    @Query(value = "select * from bill_detail join bill on bill.id_merchant = ?"
//            đang không thấy bảng status nên tạm comment
//           + "and id_satatus = 1"
            , nativeQuery = true )
    List<BillDetail> findAllOrders(Long id_merchant);
}
