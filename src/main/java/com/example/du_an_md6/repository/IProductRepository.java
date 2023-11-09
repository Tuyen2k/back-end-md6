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

    List<Product> findProductByNameContains(String name);
    @Query(value = "select * from product as p join product_category as pc on p.id_product = pc.id_product where pc.id_category = ? group by p.id_product",nativeQuery = true)
    List<Product> findProductByCategory(Long id_category);

}
