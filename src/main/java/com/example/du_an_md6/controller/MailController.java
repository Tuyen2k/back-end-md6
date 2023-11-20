package com.example.du_an_md6.controller;


import com.example.du_an_md6.model.MailStructure;

import com.example.du_an_md6.service.impl.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

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
    @GetMapping("/active/{email}")
    public RedirectView active(@PathVariable String email) {
        if (mailService.activeAccount(email)) {
            return new RedirectView("http://localhost:3000");
        }
        return new RedirectView("http://localhost:3000/404");
    }

}
