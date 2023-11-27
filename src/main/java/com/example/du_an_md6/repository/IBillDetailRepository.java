package com.example.du_an_md6.repository;

import com.example.du_an_md6.model.Bill;
import com.example.du_an_md6.model.BillDetail;
import com.example.du_an_md6.model.Merchant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
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
    @Query(value = "select * from bill_detail where id_product = ?", nativeQuery = true)
    List<BillDetail> statisticsByProduct(Long id_product);

    @Query(value = "select * from bill_detail as bd join bill as b on\n" +
            "    bd.id_bill = b.id_bill where b.id_merchant = ? and b.id_status = ?", nativeQuery = true)
    List<BillDetail> statisticsByStatus(Long id_merchant, Long id_status);

    @Query(value = "select * from bill_detail as bd join bill as b on\n" +
            "    bd.id_bill = b.id_bill where b.id_merchant = ? and b.id_account = ?", nativeQuery = true)
    List<BillDetail> statisticsByUser(Long id_merchant, Long id_user);

    //last 7
    @Query(value = "select bd.* from bill_detail as bd join bill as b on b.id_bill = bd.id_bill where b.id_merchant = ? and bd.time_purchase between ? and ?", nativeQuery = true)
    List<BillDetail> revenueByStartAndEndDay(Long id_merchant, LocalDateTime start, LocalDateTime end);


    @Query(value = "SELECT bd.* FROM bill_detail bd JOIN bill b ON bd.id_bill = b.id_bill " +
            "WHERE b.id_merchant = ? AND bd.time_purchase BETWEEN ? AND ?", nativeQuery = true)
    List<BillDetail> findByTimeRange(Long id_merchant,LocalDateTime startDate, LocalDateTime endDate);

    @Query(value = "SELECT * FROM bill_detail bd JOIN " +
            "bill b ON bd.id_bill = b.id_bill " +
            "WHERE YEAR(bd.time_purchase) = ? " +
            "AND WEEK(bd.time_purchase, 1) = ? " +
            "AND b.id_merchant = ?", nativeQuery = true)
    List<BillDetail> findByYearAndWeekAndMerchant( Integer year,
                                                   Integer week,
          Long idMerchant);

    @Query(value = "SELECT bd.* FROM bill_detail bd " +
            "JOIN bill b ON bd.id_bill = b.id_bill " +
            "WHERE YEAR(bd.time_purchase) = ? " +
            "AND MONTH(bd.time_purchase) = ? " +
            "AND b.id_merchant = ?", nativeQuery = true)
    List<BillDetail> findByMonthAndMerchant(Integer year, Integer month, Long idMerchant);


    @Query(value = "select * from bill_detail where id_bill = ?", nativeQuery = true)
    List<BillDetail> findAllByBill(Long id_bill);
}
