package com.example.du_an_md6.repository;

import com.example.du_an_md6.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IProductRepository  extends JpaRepository<Product,Long> {
    @Query(nativeQuery = true, value = "select * from product where id_product = ? and is_delete = FALSE")
    Optional<Product> findByIdActivity(Long id);
    @Query(value = "SELECT * FROM product WHERE id_merchant = ? AND is_delete = false", nativeQuery = true)
    List<Product> findProductMerchant(Long id_merchant);
    @Query(value = "SELECT p.* FROM product AS p JOIN merchant AS m ON p.id_merchant = m.id_merchant WHERE m.id_merchant = ? AND p.name LIKE %?% AND p.is_delete = false", nativeQuery = true)
    List<Product> findAllByMerchantAndNameProduct(Long id_merchant, String name);
//    @Query(nativeQuery = true,value = "select p.* from product as p\n" +
//            "join product_category pc on p.id_product = pc.id_product\n" +
//            "where p.name like ?  and pc.id_category = ? and p.is_delete = FALSE \n" +
//            "group by p.id_product ")
//    List<Product> filterProduct(@Param("name") String name, @Param("category") Long id_category );
    List<Product> findProductByNameContains(String name);
    @Query(value = "select * from product as p join product_category as pc on p.id_product = pc.id_product where pc.id_category = ? group by p.id_product",nativeQuery = true)
    List<Product> findProductByCategory(Long id_category);

    @Query(value = "select * from product where is_delete = FALSE", nativeQuery = true)
    List<Product> findAllByActivity();
}
