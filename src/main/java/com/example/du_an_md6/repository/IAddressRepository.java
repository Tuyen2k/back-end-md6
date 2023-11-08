package com.example.du_an_md6.repository;

import com.example.du_an_md6.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IAddressRepository extends JpaRepository<Address,Long> {
    @Query(nativeQuery = true, value = "select * from address order by id_address desc limit 1;")
    Address findLast();

    @Query(nativeQuery = true, value = "select * from address where id_city = ? and id_district = ? and id_ward = ? and address_detail = ?")
    Address findAddressU(Long id_city,Long id_district,Long id_ward, String address_detail);
}
