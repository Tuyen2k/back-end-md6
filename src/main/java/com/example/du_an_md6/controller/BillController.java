package com.example.du_an_md6.controller;

import com.example.du_an_md6.model.*;
import com.example.du_an_md6.model.dto.BillDetailDTO;
import com.example.du_an_md6.model.dto.OrderData;
import com.example.du_an_md6.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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
    @Autowired
    private IProductService iProductService;


    private String getCodePurchase() {
        Random random = new Random();
        return String.valueOf(random.nextInt(999999) + 100000);
    }

    @PostMapping("/order")
    public ResponseEntity<String> order(@RequestBody() OrderData orderData) {
        List<CartDetail> cartDetailList = orderData.getCartDetailList();
        List<Coupon> coupons = orderData.getCoupons();
        String codePurchase = getCodePurchase();
        List<Bill> bills = new ArrayList<>();
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
            iProductService.updatePurchase(cartDetail.getProduct().getId_product(), cartDetail.getQuantity());
            iCartDetailService.deleteCartDetail(cartDetail.getId_cartDetail());

            boolean flag = true;
            for(Bill billDB: bills){
                if (Objects.equals(billDB.getId_bill(), bill.getId_bill())) {
                    flag = false;
                    break;
                }
            }
            if (flag){
                bills.add(bill);
            }
        }
        iBillService.handleDiscount(bills, coupons);
        return ResponseEntity.ok("Order success!");
    }

    @PostMapping("/order-now/{id}/quantity/{quantity}/discount/{discount}")
    public ResponseEntity<String> orderNow(@PathVariable("id") Long id_account,
                                           @PathVariable("quantity") int quantity,
                                           @PathVariable("discount") double discount,
                                           @RequestBody Product product) {
        String codePurchase = getCodePurchase();
        Account account = iAccountService.findById(id_account);
        if (account != null) {
            Status status = iStatusService.findById(1L);
            iBillService.save(new Bill(account,
                    product.getMerchant(), status, codePurchase, LocalDateTime.now(),  discount));
            Bill bill = iBillService.findByAccountAndMerchantAndCode(account.getId_account(), product.getMerchant().getId_merchant(), codePurchase);
            BillDetail billDetail = new BillDetail(product, bill, quantity, product.getPriceSale(), bill.getTime_purchase());
            iBillDetailService.save(billDetail);
            iProductService.updatePurchase(product.getId_product(), quantity);
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

    @GetMapping("/user/{id_merchant}")
    public ResponseEntity<List<Bill>> getUserByMerchant(@PathVariable Long id_merchant){
        if (!iBillService.getAllBillyMerchant(id_merchant).isEmpty()){
            return new ResponseEntity<>(iBillService.getAllBillyMerchant(id_merchant), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping("/cancel/bill/{id}")
    public ResponseEntity<String> cancelBill(@PathVariable("id") Long id_bill){
        Bill bill = iBillService.findById(id_bill);
        if (bill == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            Status status = iStatusService.findById(6L);
            bill.setStatus(status);
            iBillService.save(bill);
            return ResponseEntity.ok("Cancel success!");
        }
    }

    @PostMapping("/update-status/{id}")
    public ResponseEntity<String> updateStatusBill(@PathVariable("id") Long id_bill,
                                                   @RequestBody Status status){
        Bill bill = iBillService.findById(id_bill);
        if (bill == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            bill.setStatus(status);
            iBillService.save(bill);
            return ResponseEntity.ok("Update status success!");
        }
    }
}
