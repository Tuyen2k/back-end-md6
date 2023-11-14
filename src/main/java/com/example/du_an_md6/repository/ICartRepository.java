package com.example.du_an_md6.repository;

import com.example.du_an_md6.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ICartRepository extends JpaRepository<Cart, Long> {

    @Query(nativeQuery = true, value = "select  * from cart where id_account = ?")
    Optional<Cart> findByAccount(Long id_account);
}
