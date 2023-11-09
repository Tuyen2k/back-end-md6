package com.example.du_an_md6.repository;

import com.example.du_an_md6.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProductRepository  extends JpaRepository<Product,Long> {
    @Query(value = "SELECT * FROM product WHERE id_merchant = ? AND is_delete = false", nativeQuery = true)
    List<Product> findProductMerchant(Long id_merchant);
    @Query(value = "SELECT p.* FROM product AS p JOIN merchant AS m ON p.id_merchant = m.id_merchant WHERE m.id_merchant = ? AND p.name LIKE %?% AND p.is_delete = false", nativeQuery = true)
    List<Product> findAllByMerchantAndNameProduct(Long id_merchant, String name);
}
