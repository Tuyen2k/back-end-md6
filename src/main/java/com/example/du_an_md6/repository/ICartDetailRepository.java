package com.example.du_an_md6.repository;

import com.example.du_an_md6.model.CartDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICartDetailRepository extends JpaRepository<CartDetail, Long> {

    @Query(value = "select * from cart_detail where id_cart = :id_cart", nativeQuery = true)
    List<CartDetail> getCartDetailByCart(@Param("id_cart") Long id_cart);
}
