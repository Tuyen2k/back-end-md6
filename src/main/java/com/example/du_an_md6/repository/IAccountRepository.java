package com.example.du_an_md6.repository;

import com.example.du_an_md6.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAccountRepository extends JpaRepository<Account, Long> {
    Account findByName(String username);
}
