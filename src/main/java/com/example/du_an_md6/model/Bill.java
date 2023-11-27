package com.example.du_an_md6.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

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
    @ManyToOne
    @JoinColumn(name = "id_status")
    private Status status;
    private String codePurchase;
    private LocalDateTime time_purchase;
    private double discount;

    public Bill() {
    }

    public Bill(Account account, Merchant merchant, Status status, String codePurchase, LocalDateTime time) {
        this.account = account;
        this.merchant = merchant;
        this.status = status;
        this.codePurchase = codePurchase;
        this.time_purchase = time;
    }

    public Bill(Account account, Merchant merchant, Status status, String codePurchase, LocalDateTime time_purchase, double discount) {
        this.account = account;
        this.merchant = merchant;
        this.status = status;
        this.codePurchase = codePurchase;
        this.time_purchase = time_purchase;
        this.discount = discount;
    }
}
