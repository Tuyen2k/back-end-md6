package com.example.du_an_md6.model.dto;


import com.example.du_an_md6.model.Role;
import lombok.Data;

@Data
public class AccountDTO {

    private Long id_account;
    private String name;
    private String phone;
    private String image;
    private boolean isDelete;
    private Role role;
    private AddressDTO addressDelivery;
}
