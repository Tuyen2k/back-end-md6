package com.example.du_an_md6.mapper;

import com.example.du_an_md6.model.Account;
import com.example.du_an_md6.model.dto.AccountDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AccountMapper implements EntityMapper<AccountDTO, Account> {

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Account toEntity(AccountDTO accountDTO) {
        return modelMapper.map(accountDTO, Account.class);
    }

    @Override
    public AccountDTO toDto(Account account) {
        return modelMapper.map(account, AccountDTO.class);
    }

    @Override
    public List<AccountDTO> toListDto(List<Account> accounts) {
        List<AccountDTO> accountsDTO = new ArrayList<>();
        for(Account account : accounts){
            AccountDTO accountDTO = modelMapper.map(account, AccountDTO.class);
            accountsDTO.add(accountDTO);
        }
        return accountsDTO;
    }

}