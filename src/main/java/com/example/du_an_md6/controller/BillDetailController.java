package com.example.du_an_md6.controller;

import com.example.du_an_md6.model.BillDetail;
import com.example.du_an_md6.service.IBillDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@CrossOrigin("*")
@RequestMapping("/api/billDetail")
public class BillDetailController {
    @Autowired
    IBillDetailService iBillDetailService;

    @GetMapping("/{id_merchant}")
    public ResponseEntity<List<BillDetail>> findAllOrders(@PathVariable Long id_merchant){
        if (!iBillDetailService.findAllOrders(id_merchant).isEmpty()){
            return new ResponseEntity<>(iBillDetailService.findAllOrders(id_merchant), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
