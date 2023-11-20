package com.example.du_an_md6.repository;

import com.example.du_an_md6.model.Account;
import com.example.du_an_md6.model.dto.AccountDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IAccountRepository extends JpaRepository<Account, Long> {
    Account findByName(String username);
    Account findByPhone(String phone);
    Account findByEmail(String email);

    @Query(value = "select a.* from account as a join merchant as m on a.id_account = m.id_account where id_merchant = ?", nativeQuery = true)
    Account findAccountByMerchant(Long id_merchant);
}
