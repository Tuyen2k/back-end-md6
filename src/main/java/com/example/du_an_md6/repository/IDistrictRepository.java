package com.example.du_an_md6.repository;

import com.example.du_an_md6.model.City;
import com.example.du_an_md6.model.District;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IDistrictRepository extends JpaRepository<District, Long> {
    List<District> findAllByCity(City city);
}
