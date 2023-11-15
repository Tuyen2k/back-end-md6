package com.example.du_an_md6.controller;

import com.example.du_an_md6.model.Coupon;
import com.example.du_an_md6.model.Merchant;
import com.example.du_an_md6.service.ICouponService;
import com.example.du_an_md6.service.IMerchantService;
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
    @Autowired
    IMerchantService merchantService;

    @GetMapping("/{id_merchant}")
    public ResponseEntity<List<Coupon>> update(@PathVariable Long id_merchant){
        return new ResponseEntity<>(couponService.findAllByIdMerchant(id_merchant), HttpStatus.OK);
    }

    @GetMapping("/findOne/{id}")
    public ResponseEntity<Coupon> findOne(@PathVariable Long id){
        if (couponService.findById(id) != null){
         return new ResponseEntity<>(couponService.findById(id), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<Void> create(@RequestBody Coupon coupon){
        Merchant merchant = merchantService.findById(coupon.getMerchant().getId_merchant());
        coupon.setMerchant(merchant);
        couponService.save(coupon);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping ("/update")
    public ResponseEntity<Void> update(@RequestBody Coupon coupon){
        couponService.save(coupon);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
       couponService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }



}
