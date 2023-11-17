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

    public BillDetail() {
    }

    public BillDetail(Product product, Bill bill, int quantity, double price, LocalDateTime time_purchase) {
        this.product = product;
        this.bill = bill;
        this.quantity = quantity;
        this.price = price;
        this.time_purchase = time_purchase;
    }
}
