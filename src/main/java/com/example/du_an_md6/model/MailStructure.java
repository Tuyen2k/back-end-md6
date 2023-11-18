package com.example.du_an_md6.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data

public class MailStructure {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_mail;
    private String subject;
    private String message;
    private String receiver;

    public MailStructure(String subject, String text, String email) {
        this.subject =subject;
        this.message = text;
        this.receiver =email;
    }

    public MailStructure() {

    }
}
