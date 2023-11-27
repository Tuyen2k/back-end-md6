package com.example.du_an_md6.model.dto;

import com.example.du_an_md6.model.Status;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BillDTO {

    private Long id_bill;
    private AccountDTO account;
    private MerchantDTO merchant;
    private Status status;
    private String codePurchase;
    private LocalDateTime time_purchase;
    private double discount;
}
