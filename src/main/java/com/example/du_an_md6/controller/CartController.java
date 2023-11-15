package com.example.du_an_md6.controller;


import com.example.du_an_md6.model.*;
import com.example.du_an_md6.model.dto.CartDetailDTO;
import com.example.du_an_md6.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/carts")
public class CartController {
    @Autowired
    private ICartDetailService iCartDetailService;
    @Autowired
    private ICartService iCartService;
    @Autowired
    private IAccountService iAccountService;
    @Autowired
    private IMerchantService iMerchantService;
    @Autowired
    private IStatusService iStatusService;

    @GetMapping("/account/{id}/status/{id_status}")
    public ResponseEntity<List<CartDetailDTO>> getAllCart(@PathVariable("id") Long id_account,
                                                          @PathVariable("id_status") Long id_status) {
        List<CartDetailDTO> cartDetails = iCartDetailService.getAllCartDetailByAccount(id_account, id_status);
        return ResponseEntity.ok(cartDetails);
    }


    @GetMapping("/account/{id}/update/{quantity}")
    public ResponseEntity<String> updateQuantityInCart(@PathVariable("id") Long id_cartDetail,
                                                       @PathVariable("quantity") int quantity) {
        CartDetail cartDetail = iCartDetailService.findById(id_cartDetail);
        cartDetail.setQuantity(quantity);
        iCartDetailService.save(cartDetail);
        return ResponseEntity.ok("Update success!");
    }

    @DeleteMapping("/account/cart-detail/{id}")
    public ResponseEntity<String> deleteCartDetail(@PathVariable("id") Long id_cart_detail) {
        iCartDetailService.deleteCartDetail(id_cart_detail);
        return ResponseEntity.ok("Delete success!");
    }

    @PostMapping("/account/{id}/create/{id_status}/status")
    public ResponseEntity<?> createCartDetail(@PathVariable("id") Long id_account,
                                              @PathVariable("id_status") Long id_status,
                                              @RequestBody CartDetail cartDetail) {
        Cart cart = iCartService.findCartByAccountAndMerchantAndStatus(id_account,
                cartDetail.getProduct().getMerchant().getId_merchant(), id_status);
        Account account = iAccountService.findById(id_account);
        cartDetail.setAddress(account.getAddressDelivery());

        //cart null => create cart

        if (Objects.equals(cart, null)) {

            Merchant merchant = iMerchantService.findById(cartDetail.getProduct().getMerchant().getId_merchant());
            Status status = iStatusService.findById(id_status);
            iCartService.save(new Cart(account, merchant, status));
            cart = iCartService.findCartByAccountAndMerchantAndStatus(id_account, merchant.getId_merchant(), id_status);
            cartDetail.setCart(cart);
        }
        //cart != null => check cart detail
        else {
            cartDetail.setCart(cart);
            CartDetail cartDetailDB = iCartDetailService.getCartDetailByCartAndProduct(cart.getId_cart(),
                    cartDetail.getProduct().getId_product());
            if (!Objects.equals(cartDetailDB, null)) {
                cartDetail.setId_cartDetail(cartDetailDB.getId_cartDetail());
                cartDetail.setQuantity(cartDetailDB.getQuantity() + cartDetail.getQuantity());
            }
        }
        iCartDetailService.save(cartDetail);
        return ResponseEntity.ok("Create cart detail success!");
    }


}
