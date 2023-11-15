package com.example.du_an_md6.model.dto;

import com.example.du_an_md6.model.Account;
import com.example.du_an_md6.model.Merchant;
import com.example.du_an_md6.model.Status;
import lombok.Data;

@Data
public class BillDTO {

    private Long id_bill;

    private AccountDTO account;

    private MerchantDTO merchant;

    private Status status;
}
