package com.example.du_an_md6.controller;
import com.example.du_an_md6.model.BillDetail;
import com.example.du_an_md6.model.Merchant;
import com.example.du_an_md6.service.IBillDetailService;
import com.example.du_an_md6.service.IMerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
@RestController
@CrossOrigin("*")
@RequestMapping("/api/billDetail")
public class BillDetailController {
    @Autowired
    IBillDetailService iBillDetailService;
    @Autowired
    IMerchantService iMerchantService;

    @GetMapping("/{id_merchant}")
    public ResponseEntity<List<BillDetail>> findAllOrders(@PathVariable Long id_merchant){
        if (!iBillDetailService.findAllOrders(id_merchant).isEmpty()){
            return new ResponseEntity<>(iBillDetailService.findAllOrders(id_merchant), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/search/{id_merchant}")
    public ResponseEntity<List<BillDetail>> findAllOrders(@RequestParam String name, @PathVariable Long id_merchant){
        List<BillDetail> billDetails = new ArrayList<>();
        Merchant merchant = iMerchantService.findById(id_merchant);
        if (merchant != null){
            if (!iBillDetailService.findByBill_Account_NameContainingAndBill_Merchant(name, merchant).isEmpty()){
               List<BillDetail> listName = iBillDetailService.findByBill_Account_NameContainingAndBill_Merchant(name, merchant);
                billDetails.addAll(listName);
            }
        }  if (!iBillDetailService.findByBill_Account_PhoneContainingAndBill_Merchant(name, merchant).isEmpty()){
            List<BillDetail> listPhone = iBillDetailService.findByBill_Account_PhoneContainingAndBill_Merchant(name, merchant);
            billDetails.addAll(listPhone);
        } return new ResponseEntity<>(billDetails, HttpStatus.OK);
    }
}
