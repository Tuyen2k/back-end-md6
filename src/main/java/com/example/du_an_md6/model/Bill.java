package com.example.du_an_md6.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_bill;
    @ManyToOne
    @JoinColumn(name = "id_account")
    private Account account;
    @ManyToOne
    @JoinColumn(name = "id_merchant")
    private Merchant merchant;
}
