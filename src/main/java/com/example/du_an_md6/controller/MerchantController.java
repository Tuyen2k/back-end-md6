package com.example.du_an_md6.controller;

import com.example.du_an_md6.mapper.MerchantMapper;
import com.example.du_an_md6.model.Account;
import com.example.du_an_md6.model.Activity;
import com.example.du_an_md6.model.Address;
import com.example.du_an_md6.model.Merchant;
import com.example.du_an_md6.model.dto.MerchantDTO;
import com.example.du_an_md6.repository.IActivityRepository;
import com.example.du_an_md6.service.IMerchantService;
import com.example.du_an_md6.service.impl.AccountService;
import com.example.du_an_md6.service.impl.AddressServiceImpl;
import com.example.du_an_md6.service.impl.MerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/merchants")
public class MerchantController {

    @Autowired
    IMerchantService merchantService;
    @Autowired
    MerchantMapper merchantMapper;
    @Autowired
    AddressServiceImpl addressService;
    @Autowired
    AccountService accountService;
    @Autowired
    MerchantService merchant;
    @Autowired
    IActivityRepository activityRepository;

    @GetMapping
    public ResponseEntity<List<MerchantDTO>> findAll() {
        List<MerchantDTO> merchantDTOS = merchantMapper.toListDto(merchantService.findAll());
        return new ResponseEntity<>(merchantDTOS, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MerchantDTO> findOne(@PathVariable Long id) {
        Merchant merchant = merchantService.findById(id);
        if (merchant != null) {
            return new ResponseEntity<>(merchantMapper.toDto(merchant), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/activity")
    public ResponseEntity<List<Activity>> findAllActivity() {
        List<Activity> list = new ArrayList<>();
        for (Activity activity : activityRepository.findAll()) {
            if (activity.getId_activity() != 2) {
                list.add(activity);
            }
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/account/{id}")
    public ResponseEntity<MerchantDTO> findOneByAccount(@PathVariable Long id) {
        Account account = accountService.findById(id);
        if (account != null) {
            return new ResponseEntity<>(merchantMapper.toDto
                    (merchant.findMerchantByIdAcc(id)), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody Merchant merchant) {
        addressService.save(merchant.getAddressShop());
        Address address = addressService.findAddressU(merchant.getAddressShop().
                        getCity().getId_city(),
                merchant.getAddressShop().getDistrict().getId_district(),
                merchant.getAddressShop().getWard().getId_ward(),
                merchant.getAddressShop().getAddress_detail());
        merchant.setAddressShop(address);
        merchantService.save(merchant);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Void> updateMerchant(@RequestBody Merchant merchant) {

        addressService.save(merchant.getAddressShop());
        Address address = addressService.findAddressU(merchant.getAddressShop().
                        getCity().getId_city(),
                merchant.getAddressShop().getDistrict().getId_district(),
                merchant.getAddressShop().getWard().getId_ward(),
                merchant.getAddressShop().getAddress_detail());
        merchant.setAddressShop(address);

        merchantService.save(merchant);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
