package com.example.du_an_md6.controller;


import com.example.du_an_md6.model.MailStructure;
import com.example.du_an_md6.service.impl.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/mail")
public class MailController {

    @Autowired
    private MailService mailService;

    @PostMapping("/send")
    public ResponseEntity<String> sendMail(@RequestBody MailStructure mailStructure){
        mailService.sendMail(mailStructure);
        mailService.save(mailStructure);
        return ResponseEntity.ok("Send mail successfully!!");
    }

}
