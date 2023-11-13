package com.example.du_an_md6.controller;


import com.example.du_an_md6.model.Cart;
import com.example.du_an_md6.model.CartDetail;
import com.example.du_an_md6.service.ICartDetailService;
import com.example.du_an_md6.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/carts")
public class CartController {
    @Autowired
    private ICartDetailService iCartDetailService;
    @Autowired
    private ICartService iCartService;

    @GetMapping("/account/{id}")
    public ResponseEntity<List<CartDetail>> getAllCart(@PathVariable("id") Long id_account){
        Cart cart = iCartService.findCartByAccount(id_account);
        List<CartDetail> cartDetails = iCartDetailService.getAllCartDetailByCart(cart.getId_cart());
        return ResponseEntity.ok(cartDetails);
    }
}
