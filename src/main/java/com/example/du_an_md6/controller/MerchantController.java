package com.example.du_an_md6.controller;

import com.example.du_an_md6.jwt.service.JwtResponse;
import com.example.du_an_md6.jwt.service.JwtService;
import com.example.du_an_md6.mapper.MerchantMapper;
import com.example.du_an_md6.model.*;
import com.example.du_an_md6.model.dto.MerchantDTO;
import com.example.du_an_md6.repository.IActivityRepository;
import com.example.du_an_md6.service.IMerchantService;
import com.example.du_an_md6.service.IRoleService;
import com.example.du_an_md6.service.impl.AccountService;
import com.example.du_an_md6.service.impl.AddressServiceImpl;
import com.example.du_an_md6.service.impl.MerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/merchants")
public class MerchantController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtService jwtService;
    @Autowired
    IMerchantService merchantService;
    @Autowired
    MerchantMapper merchantMapper;
    @Autowired
    AddressServiceImpl addressService;
    @Autowired
    AccountService accountService;
    @Autowired
    private IRoleService iRoleService;
    @Autowired
    MerchantService merchant;
    @Autowired
    IActivityRepository activityRepository;

    @GetMapping
    public ResponseEntity<List<MerchantDTO>> findAll() {
        List<MerchantDTO> merchantDTOS = merchantMapper.toListDto(merchantService.findAll());
        return new ResponseEntity<>(merchantDTOS, HttpStatus.OK);
    }

    @GetMapping("/merchant")
    public ResponseEntity<List<MerchantDTO>> findAllCheckDelete() {
        return new ResponseEntity<>(merchantService.findAllCheckDelete(), HttpStatus.OK);
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
        Role role = iRoleService.findById(3L);
        merchant.getAccount().setRole(role);
        Account account = accountService.findById(merchant.getAccount().getId_account());
        account.setRole(role);
        accountService.save(account);
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

    @GetMapping("/find-merchant/{id}")
    public ResponseEntity<MerchantDTO> findMerchantByAccount(@PathVariable("id") Long id_account){
        return ResponseEntity.ok(merchantService.findByAccount(id_account));
    }
}
