package com.example.du_an_md6.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_cart;
    @ManyToOne
    @JoinColumn(name = "id_account")
    private Account account;
    @ManyToOne
    @JoinColumn(name = "id_merchant")
    private Merchant merchant;
    @ManyToOne
    @JoinColumn(name = "id_status")
    private Status status;

    public Cart() {
    }

    public Cart(Account account, Merchant merchant) {
        this.account = account;
        this.merchant = merchant;
    }
}
