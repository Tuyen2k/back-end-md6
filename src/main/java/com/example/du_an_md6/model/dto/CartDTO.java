package com.example.du_an_md6.model.dto;

import com.example.du_an_md6.model.Status;
import lombok.Data;

@Data
public class CartDTO {

    private Long id_cart;

    private AccountDTO account;

    private MerchantDTO merchant;

    private Status status;
}
