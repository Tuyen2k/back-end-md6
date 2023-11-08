package com.example.du_an_md6.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
public class BillDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_billDetail;
    @ManyToOne
    @JoinColumn(name = "id_product")
    private Product product;
    @ManyToOne
    @JoinColumn(name = "id_bill")
    private Bill bill;
    private int quantity;
    private double price;
    private LocalDateTime time_purchase;
}
