package com.example.du_an_md6.controller;

import com.example.du_an_md6.model.*;
import com.example.du_an_md6.model.dto.BillDetailDTO;
import com.example.du_an_md6.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

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


    private String getCodePurchase() {
        Random random = new Random();
        return String.valueOf(random.nextInt(999999) + 100000);
    }

    @PostMapping("/order")
    public ResponseEntity<String> order(@RequestBody List<CartDetail> cartDetailList) {
        String codePurchase = getCodePurchase();
        for (CartDetail cartDetail : cartDetailList) {
            Bill bill = iBillService.findByAccountAndMerchantAndCode(cartDetail.getCart().getAccount().getId_account(),
                    cartDetail.getCart().getMerchant().getId_merchant(), codePurchase);
            if (bill == null) {
                Status status = iStatusService.findById(1L);
                iBillService.save(new Bill(cartDetail.getCart().getAccount(),
                        cartDetail.getCart().getMerchant(), status, codePurchase, LocalDateTime.now()));
                bill = iBillService.findByAccountAndMerchantAndCode(cartDetail.getCart().getAccount().getId_account(),
                        cartDetail.getCart().getMerchant().getId_merchant(), codePurchase);

            }
            BillDetail billDetail = new BillDetail(cartDetail.getProduct(), bill, cartDetail.getQuantity(), cartDetail.getPrice(), bill.getTime_purchase());
            iBillDetailService.save(billDetail);
            iCartDetailService.deleteCartDetail(cartDetail.getId_cartDetail());
        }
        return ResponseEntity.ok("Order success!");
    }

    @PostMapping("/order-now/{id}")
    public ResponseEntity<String> orderNow(@PathVariable("id") Long id_account, @RequestBody Product product) {
        String codePurchase = getCodePurchase();
        Account account = iAccountService.findById(id_account);
        if (account != null) {
            Status status = iStatusService.findById(1L);
            iBillService.save(new Bill(account,
                    product.getMerchant(), status, codePurchase, LocalDateTime.now() ));
            Bill bill = iBillService.findByAccountAndMerchantAndCode(account.getId_account(), product.getMerchant().getId_merchant(), codePurchase);
            BillDetail billDetail = new BillDetail(product, bill, 1, product.getPriceSale(), bill.getTime_purchase());
            iBillDetailService.save(billDetail);
            return ResponseEntity.ok("Order success!");
        }
        return ResponseEntity.ok("Order error!");
    }

    @GetMapping("/all/account/{id}")
    public ResponseEntity<List<BillDetailDTO>> getAllBillDetailByAccount(@PathVariable("id") Long id_account){
        List<BillDetailDTO> list = iBillDetailService.getAddBillDetailByAccount(id_account);
        if (list.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            return ResponseEntity.ok(list);
        }
    }





}
