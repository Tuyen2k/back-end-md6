package com.example.du_an_md6.service;

import com.example.du_an_md6.model.Account;
import com.example.du_an_md6.model.dto.AccountDTO;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IAccountService extends IGenerateService<Account> {
    void delete(Long id);
    List<AccountDTO> findAllDTO();
    Account findByPhone(String phone);
    Account findByEmail(String email);
    AccountDTO findAccountByMerchant(Long id_merchant);

}
