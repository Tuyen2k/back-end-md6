package com.example.du_an_md6.repository;
import com.example.du_an_md6.model.Ward;
import com.example.du_an_md6.model.District;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IWardRepository extends JpaRepository<Ward, Long> {
    List<Ward> findAllByDistrict(District district);
}
