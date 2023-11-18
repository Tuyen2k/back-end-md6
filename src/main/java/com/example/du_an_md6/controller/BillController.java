package com.example.du_an_md6.controller;

import com.example.du_an_md6.model.*;
import com.example.du_an_md6.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/bill")
public class BillController {

    @Autowired
    private IBillService iBillService;
    @Autowired
    private IBillDetailService iBillDetailService;
    @Autowired
    private IStatusService iStatusService;
    @Autowired
    private ICartDetailService iCartDetailService;
    @Autowired
    private IAccountService iAccountService;



    @PostMapping("/order")
    public ResponseEntity<String> order(@RequestBody List<CartDetail> cartDetailList) {
        for (CartDetail cartDetail : cartDetailList) {
            Bill bill = iBillService.findByAccountAndMerchant(cartDetail.getCart().getAccount().getId_account(),
                    cartDetail.getCart().getMerchant().getId_merchant());
//            Bill bill = iBillService.findByAccountAndMerchant(cartDetail.getCart().getAccount().getId_account(),
//                    cartDetail.getCart().getMerchant().getId_merchant(), LocalDateTime.now());
            if (bill == null) {
                Status status = iStatusService.findById(1L);
                iBillService.save(new Bill(cartDetail.getCart().getAccount(),
                        cartDetail.getCart().getMerchant(), status, LocalDateTime.now()));
                bill = iBillService.findByAccountAndMerchant(cartDetail.getCart().getAccount().getId_account(),
                        cartDetail.getCart().getMerchant().getId_merchant());

            }
            BillDetail billDetail = new BillDetail(cartDetail.getProduct(),bill,cartDetail.getQuantity(), cartDetail.getPrice(), bill.getTime_purchase());
            iBillDetailService.save(billDetail);
            iCartDetailService.deleteCartDetail(cartDetail.getId_cartDetail());
        }
        return ResponseEntity.ok("Order success!");
    }

    @PostMapping("/order-now/{id}")
    public ResponseEntity<String> orderNow(@PathVariable("id") Long id_account,@RequestBody Product product){
        Account account = iAccountService.findById(id_account);
        if (account != null){
            Status status = iStatusService.findById(1L);
            iBillService.save(new Bill(account,
                    product.getMerchant(), status, LocalDateTime.now()));
            Bill bill = iBillService.findByAccountAndMerchant(account.getId_account(), product.getMerchant().getId_merchant());
            BillDetail billDetail = new BillDetail(product,bill,1, product.getPrice(), bill.getTime_purchase());
            iBillDetailService.save(billDetail);
            return ResponseEntity.ok("Order success!");
        }
        return ResponseEntity.ok("Order error!");
    }


}
