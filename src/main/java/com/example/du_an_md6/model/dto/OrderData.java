package com.example.du_an_md6.model.dto;

import com.example.du_an_md6.model.CartDetail;
import com.example.du_an_md6.model.Coupon;
import lombok.Data;

import java.util.List;

@Data
public class OrderData {
    private List<CartDetail> cartDetailList;
    private List<Coupon> coupons;
}
