package com.example.du_an_md6.controller;

import com.example.du_an_md6.model.Coupon;
import com.example.du_an_md6.service.ICouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/coupons")
public class CouponController {
    @Autowired
    ICouponService couponService;

    @GetMapping("/{id_merchant}")
    public ResponseEntity<List<Coupon>> findAll(@PathVariable Long id_merchant){
        return new ResponseEntity<>(couponService.findAllByIdMerchant(id_merchant), HttpStatus.OK);
    }


}
