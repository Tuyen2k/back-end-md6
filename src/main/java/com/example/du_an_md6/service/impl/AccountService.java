package com.example.du_an_md6.service.impl;

import com.example.du_an_md6.mapper.AccountMapper;
import com.example.du_an_md6.model.Account;
import com.example.du_an_md6.model.AccountPrinciple;
import com.example.du_an_md6.model.dto.AccountDTO;
import com.example.du_an_md6.repository.IAccountRepository;
import com.example.du_an_md6.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService implements UserDetailsService, IAccountService {
    @Autowired
    private IAccountRepository iAccountRepository;

    @Autowired
    private AccountMapper accountMapper;
    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public List<Account> findAll() {
        return iAccountRepository.findAll();
    }

    @Override
    public Account findById(Long id) {
        return iAccountRepository.findById(id).orElse(null);
    }

    @Override
    public void save(Account account) {
        iAccountRepository.save(account);
    }


    public Account findByUsername(String name) {
        return iAccountRepository.findByName(name);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account user = findByUsername(username);
        if (user != null) {
            return AccountPrinciple.build(user);
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        Account account = findById(id);
        account.setDelete(true);
        iAccountRepository.save(account);
    }

    @Override
    public List<AccountDTO> findAllDTO() {
        List<Account> list = iAccountRepository.findAll();
        return accountMapper.toListDto(list);
    }


    @Override
    public Account findByPhone(String phone) {
        return iAccountRepository.findByPhone(phone);
    }

    @Override
    public Account findByEmail(String email) {
        return iAccountRepository.findByEmail(email);
    }

    public Boolean changePassword(Account account){
        Account account1 = iAccountRepository.findById(account.getId_account()).orElse(null);
        if (account1!=null && account1.getPassword().equals(account1.getConfirmPassword())){
            account.setPassword(account.getPassword());
            iAccountRepository.save(account);
            return true;
        }
        return false;
    }


    @Override
    public AccountDTO findAccountByMerchant(Long id_merchant) {
        return accountMapper.toDto(iAccountRepository.findAccountByMerchant(id_merchant));
    }
}
