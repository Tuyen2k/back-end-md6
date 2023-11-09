package com.example.du_an_md6.repository;

import com.example.du_an_md6.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProductRepository  extends JpaRepository<Product,Long> {
    @Query(value = "select * from product where id_merchant = ? ",nativeQuery = true)
    List<Product> findProductMerchant( Long  id_merchant);
    @Query(nativeQuery = true,value = "select p.* from product as p\n" +
            "join product_category pc on p.id_product = pc.id_product\n" +
            "where p.name like ?  and pc.id_category = ? \n" +
            "group by p.id_product ")
    List<Product> filterProduct(@Param("name") String name, @Param("category") Long id_category );
}
