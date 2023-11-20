package com.example.du_an_md6.repository;

import com.example.du_an_md6.model.Bill;
import com.example.du_an_md6.model.BillDetail;
import com.example.du_an_md6.model.Merchant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

import java.util.List;
import java.util.Map;

@Repository
public interface IBillDetailRepository extends JpaRepository<BillDetail, Long> {
    @Query(value = "select bd.* from bill_detail as bd join bill as b on bd.id_bill = b.id_bill where id_account = ? order by id_bill desc", nativeQuery = true)
    List<BillDetail> getAddBillDetailByAccount(Long id_account);
    @Query(value = "select bd.* from bill_detail as bd\n" +
            "join bill as b on bd.id_bill = b.id_bill\n" +
            "where id_merchant = ? order by id_bill desc "
            , nativeQuery = true )
    List<BillDetail> findAllOrders(Long id_merchant);

    List<BillDetail> findByBill_Account_NameContainingAndBill_Merchant(String name, Merchant Merchant);
    List<BillDetail> findByBill_Account_PhoneContainingAndBill_Merchant(String name, Merchant Merchant);


}
