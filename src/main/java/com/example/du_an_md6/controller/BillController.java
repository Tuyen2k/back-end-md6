package com.example.du_an_md6.controller;

import com.example.du_an_md6.model.Bill;
import com.example.du_an_md6.model.BillDetail;
import com.example.du_an_md6.model.CartDetail;
import com.example.du_an_md6.model.Status;
import com.example.du_an_md6.repository.IBillDetailRepository;
import com.example.du_an_md6.service.IBillDetailService;
import com.example.du_an_md6.service.IBillService;
import com.example.du_an_md6.service.IStatusService;
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



    @PostMapping("/order")
    public ResponseEntity<String> order(@RequestBody List<CartDetail> cartDetailList) {
        for (CartDetail cartDetail : cartDetailList) {
            Bill bill = iBillService.findByAccountAndMerchant(cartDetail.getCart().getAccount().getId_account(),
                    cartDetail.getCart().getMerchant().getId_merchant());
            if (bill == null) {
                Status status = iStatusService.findById(1L);
                iBillService.save(new Bill(cartDetail.getCart().getAccount(),
                        cartDetail.getCart().getMerchant(), status, LocalDateTime.now()));
                bill = iBillService.findByAccountAndMerchant(cartDetail.getCart().getAccount().getId_account(),
                        cartDetail.getCart().getMerchant().getId_merchant());

            }
            BillDetail billDetail = new BillDetail(cartDetail.getProduct(),bill,cartDetail.getQuantity(), cartDetail.getPrice(), bill.getTime_purchase());
            iBillDetailService.save(billDetail);
        }
        return ResponseEntity.ok("Order success!");
    }


}
