package com.example.du_an_md6.model.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BillDetailDTO {
    private Long id_billDetail;
    private ProductDTO product;
    private BillDTO bill;
    private int quantity;
    private double price;
    private LocalDateTime time_purchase;
}
