package com.example.du_an_md6.repository;

import com.example.du_an_md6.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProductRepository  extends JpaRepository<Product,Long> {
    @Query(value = "select * from product where id_merchant = ? ",nativeQuery = true)
    List<Product> findProductMerchant( Long  id_merchant);
}
