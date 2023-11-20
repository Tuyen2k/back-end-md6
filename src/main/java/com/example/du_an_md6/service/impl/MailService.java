package com.example.du_an_md6.service.impl;

import com.example.du_an_md6.model.Account;
import com.example.du_an_md6.model.MailStructure;
import com.example.du_an_md6.repository.IAccountRepository;
import com.example.du_an_md6.repository.IMailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class MailService {

    @Autowired
    private IMailRepository iMailRepository;
    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private IAccountRepository accountRepository;

    @Value("${spring.mail.username}")
    private String fromMail;
    public void sendMail(MailStructure mailStructure){
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(fromMail);
        simpleMailMessage.setSubject(mailStructure.getSubject());
        simpleMailMessage.setText(mailStructure.getMessage());
        simpleMailMessage.setTo(mailStructure.getReceiver());

        mailSender.send(simpleMailMessage);
    }

    public void save(MailStructure mailStructure){
        iMailRepository.save(mailStructure);
    }
    public Boolean activeAccount(String email){
        Account account = accountRepository.findByEmail(email);
        if (account != null){
            account.setStatus(true);
            accountRepository.save(account);
            return true;
        }
        return false;
    }
    public Account findAccountByEmail(String email){
        return accountRepository.findByEmail(email);
    }
    public Boolean register(Account account) {
        String email = account.getEmail();
        String name = account.getName();
        String phone = account.getPhone();
        if(isDuplicateEmail(email)){
            return false;
        }
        if(isDuplicateName(name)){
            return false;
        }
        if(isDuplicatePhone(phone)){
            return false;
        }
        String link = "http://localhost:8080/api/mail/active/" + email;
        String subject = "Active account from Yummy";
        String text = "Hello, " + name
                + "\n Please confirm this link to active your account: "+link;
        if (findAccountByEmail(email) == null ){
            account.setStatus(false);
            accountRepository.save(account);
            MailStructure mailStructure =new MailStructure(subject,text,email);
            sendMail(mailStructure);
            return true;
        }
        return false;
    }
    public String generateRandomCode() {
        int length = 8;
        String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            sb.append(characters.charAt(index));
        }

        return sb.toString();
    }
    public boolean isDuplicateName(String name) {
        Account existingAccount = accountRepository.findByName(name);
        return existingAccount != null;
    }

    public boolean isDuplicatePhone(String phone) {
        Account existingAccount = accountRepository.findByPhone(phone);
        return existingAccount != null;
    }

    public boolean isDuplicateEmail(String email) {
        Account existingAccount = accountRepository.findByEmail(email);
        return existingAccount != null;
    }

}

