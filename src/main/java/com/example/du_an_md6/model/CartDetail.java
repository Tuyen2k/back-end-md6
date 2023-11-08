package com.example.du_an_md6.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class CartDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_cartDetail;
    @ManyToOne
    @JoinColumn(name = "id_cart")
    private Cart cart;
    @ManyToOne
    @JoinColumn(name = "id_product")
    private Product product;
    private int quantity;
    private double price;
    @ManyToOne
    @JoinColumn(name = "id_address")
    private Address address;

}
