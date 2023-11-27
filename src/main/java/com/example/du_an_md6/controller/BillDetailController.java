package com.example.du_an_md6.controller;
import com.example.du_an_md6.model.Bill;
import com.example.du_an_md6.model.BillDetail;
import com.example.du_an_md6.model.Merchant;
import com.example.du_an_md6.model.Status;
import com.example.du_an_md6.model.dto.BillDetailDTO;
import com.example.du_an_md6.service.IBillDetailService;
import com.example.du_an_md6.service.IMerchantService;
import com.example.du_an_md6.service.IStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
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
    @Autowired
    IStatusService statusService;

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

    @GetMapping("/status")
    public ResponseEntity<List<Status>> findAllStatus(){
        return new ResponseEntity<>(statusService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/SearchByProduct/{id_product}")
    public ResponseEntity<List<BillDetail>> getOrderByProduct(@PathVariable Long id_product) {
        if (!iBillDetailService.statisticsByProduct(id_product).isEmpty()){
            return new ResponseEntity<>(iBillDetailService.statisticsByProduct(id_product), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }
    }

    @GetMapping("/search_status/{id_merchant}/{id_status}")
    public ResponseEntity<List<BillDetail>> getOrderByStatus(@PathVariable Long id_merchant, @PathVariable Long id_status) {
        if (!iBillDetailService.statisticsByStatus(id_merchant, id_status).isEmpty()){
            return new ResponseEntity<>(iBillDetailService.statisticsByStatus(id_merchant, id_status), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/search_user/{id_merchant}/{id_user}")
    public ResponseEntity<List<BillDetail>> getOrderByUser(@PathVariable Long id_merchant, @PathVariable Long id_user) {
        if (!iBillDetailService.statisticsByUser(id_merchant, id_user).isEmpty()){
            return new ResponseEntity<>(iBillDetailService.statisticsByUser(id_merchant, id_user), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/revenue-last-seven/{id}")
    public ResponseEntity<List<BillDetail>> revenueLastSeven(@PathVariable("id") Long id_merchant){
        LocalDateTime end = LocalDateTime.now().withHour(0).withMinute(0).withSecond(0);
        LocalDateTime start = end.minusDays(7);

        List<BillDetail> list = iBillDetailService.revenueByStartAndEndDay(id_merchant, start, end);
        if (list == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            return ResponseEntity.ok(list);
        }
    }

    @GetMapping("/merchant/{id_merchant}/{startDate}/{endDate}")
    public ResponseEntity<List<BillDetail>> findByTimeRange(
            @PathVariable("id_merchant") Long idMerchant,
            @PathVariable("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @PathVariable("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {
        List<BillDetail> billDetails = iBillDetailService.findByTimeRange(idMerchant, startDate, endDate);
        return ResponseEntity.ok(billDetails);
    }


    @GetMapping("/merchant/{id_merchant}/year/{year}/week/{week}")
    public ResponseEntity<List<BillDetail>> findByYearAndWeekAndMerchant(
            @PathVariable("id_merchant") Long idMerchant,
            @PathVariable("year") Integer year ,
            @PathVariable("week")  Integer week ) {
        List<BillDetail> billDetails = iBillDetailService.findByYearAndWeekAndMerchant(year, week, idMerchant);
        return ResponseEntity.ok(billDetails);
    }
    @GetMapping("/merchant/{id_merchant}/year/{year}/month/{month}")
    public ResponseEntity<List<BillDetail>> findMonthByMerchant(
            @PathVariable Long id_merchant,
            @PathVariable Integer year ,
            @PathVariable Integer month) {
        List<BillDetail> billDetails = iBillDetailService.findByMonthAndMerchant(year,month,id_merchant);
        return ResponseEntity.ok(billDetails);
    }

    @GetMapping("/merchant/{id_merchant}/quarter/{valueQuarter}")
    public ResponseEntity<List<BillDetail>> findByQuarterAndMerchant(
            @PathVariable Long id_merchant,
            @PathVariable Integer valueQuarter ) {
        LocalDateTime startDate = null;
        LocalDateTime endDate = null;
        if (valueQuarter == 1){
            startDate = LocalDateTime.parse("2023-01-01T00:00:00");
            endDate = LocalDateTime.parse("2023-03-31T00:00:00");
        } else if (valueQuarter == 2){
            startDate = LocalDateTime.parse("2023-04-01T00:00:00");
            endDate = LocalDateTime.parse("2023-06-30T00:00:00");
        } else if (valueQuarter == 3){
            startDate = LocalDateTime.parse("2023-07-01T00:00:00");
            endDate = LocalDateTime.parse("2023-09-30T00:00:00");
        } else if (valueQuarter == 4){
            startDate = LocalDateTime.parse("2023-10-01T00:00:00");
            endDate = LocalDateTime.parse("2023-12-31T00:00:00");
        }
        if (startDate != null){
            List<BillDetail> billDetails = iBillDetailService.findByTimeRange(id_merchant, startDate, endDate);
            return ResponseEntity.ok(billDetails);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
