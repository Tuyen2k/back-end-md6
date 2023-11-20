package com.example.du_an_md6.repository;

import com.example.du_an_md6.model.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface IBillRepository extends JpaRepository<Bill, Long> {

    @Query(value = "select * from bill where id_account = ? and id_merchant = ? and id_status = 1",nativeQuery = true)
    Optional<Bill> findByAccountAndMerchant(Long id_account, Long id_merchant);
    @Query(value = "select * from bill where id_account = ? and id_merchant = ? and id_status = 1 and code_purchase = ?",nativeQuery = true)
    Optional<Bill> findByAccountAndMerchantAndCode(Long id_account, Long id_merchant, String code);

    @Query(value = "select * from bill where id_merchant = ?",nativeQuery = true)
    List<Bill> getBillByMerchant(Long id_merchant);
}
