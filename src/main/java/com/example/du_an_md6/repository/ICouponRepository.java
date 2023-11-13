package com.example.du_an_md6.repository;

import com.example.du_an_md6.model.Coupon;
import com.example.du_an_md6.model.Merchant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ICouponRepository extends JpaRepository<Coupon, Long> {
//        @Query(value = "SELECT c.id, c.name, c.image, c.discount_amount, c.percentage_discount, c.quantity FROM coupon c JOIN merchant m ON c.id_merchant = m.id WHERE m.id = :idMerchant", nativeQuery = true)
        List<Coupon> findCouponByMerchant(Merchant merchant);

}