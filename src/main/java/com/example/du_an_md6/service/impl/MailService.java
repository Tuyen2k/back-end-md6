package com.example.du_an_md6.service.impl;

import com.example.du_an_md6.model.MailStructure;
import com.example.du_an_md6.repository.IMailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {

    @Autowired
    private IMailRepository iMailRepository;
    @Autowired
    private JavaMailSender mailSender;
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
}
