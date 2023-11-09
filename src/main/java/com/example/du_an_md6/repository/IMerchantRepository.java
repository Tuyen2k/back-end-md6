package com.example.du_an_md6.repository;

import com.example.du_an_md6.model.Merchant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IMerchantRepository extends JpaRepository<Merchant, Long> {
}
