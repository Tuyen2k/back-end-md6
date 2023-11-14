package com.example.du_an_md6.repository;

import com.example.du_an_md6.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ICartRepository extends JpaRepository<Cart, Long> {

    @Query(nativeQuery = true, value = "select  * from cart where id_account = ? and id_status = 7")
    Optional<Cart> findByAccount(Long id_account);

    @Query(nativeQuery = true, value = "select  * from cart where id_account = ? and id_merchant = ? and id_status = ?")
    Optional<Cart> findByAccountAndMerchant(Long id_account, Long id_merchant, Long id_status);
}
