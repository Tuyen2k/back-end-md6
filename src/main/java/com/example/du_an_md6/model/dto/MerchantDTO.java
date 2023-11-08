package com.example.du_an_md6.model.dto;
import com.example.du_an_md6.model.Activity;
import com.example.du_an_md6.model.Address;
import lombok.Data;

import java.time.LocalTime;

@Data
public class MerchantDTO {
    private Long id_merchant;
    private String name;
    private String phone;
    private String email;
    private LocalTime open_time;
    private LocalTime close_time;
    private boolean isDelete;
    private Activity activity;
    private Address addressShop;
    private String image;
}
