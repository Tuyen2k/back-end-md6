package com.example.du_an_md6.repository;

import com.example.du_an_md6.model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICityRepository extends JpaRepository<City, Long> {
}
