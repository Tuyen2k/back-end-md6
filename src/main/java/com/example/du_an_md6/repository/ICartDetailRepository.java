package com.example.du_an_md6.repository;

import com.example.du_an_md6.model.CartDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICartDetailRepository extends JpaRepository<CartDetail, Long> {

    @Query(value = "select cd.* from cart_detail as cd " +
            "join cart as c on cd.id_cart = c.id_cart " +
            "where c.id_account = ? and c.id_status = ? order by cd.id_cart desc", nativeQuery = true)
    List<CartDetail> getCartDetailByAccount(Long id_cart, Long id_status);

    @Query(value = "select * from cart_detail where id_cart = ? and id_product = ?", nativeQuery = true)
    CartDetail getCartDetailByCartAndProduct( Long id_cart, Long id_product);
}
