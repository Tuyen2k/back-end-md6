package com.example.du_an_md6.controller;


import com.example.du_an_md6.jwt.service.JwtResponse;
import com.example.du_an_md6.jwt.service.JwtService;
import com.example.du_an_md6.model.Account;
import com.example.du_an_md6.model.MailStructure;
import com.example.du_an_md6.model.Role;
import com.example.du_an_md6.model.dto.AccountDTO;
import com.example.du_an_md6.service.IAccountService;
import com.example.du_an_md6.service.IRoleService;
import com.example.du_an_md6.service.impl.AccountService;
import com.example.du_an_md6.service.impl.AddressService;
import com.example.du_an_md6.service.impl.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/accounts")
public class AccountController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AccountService accountServiceLogin;
    @Autowired
    private AddressService addressService;

    @Autowired
    private IAccountService accountService;

    @Autowired
    private IRoleService roleService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private MailService mailService;

    @GetMapping
    public ResponseEntity<List<AccountDTO>> findAll() {
        return new ResponseEntity<>(accountService.findAllDTO(), HttpStatus.OK);
    }
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<String> register(@RequestBody Account user) {
        if (user.getPassword().equals(user.getConfirmPassword())){
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setConfirmPassword(passwordEncoder.encode(user.getConfirmPassword()));
            Role role_user = roleService.findById(2L);
            user.setRole(role_user);
            addressService.save(user.getAddressDelivery());
            user.setAddressDelivery(addressService.findLast());
            if (mailService.register(user)){
                return new ResponseEntity<>("Register successfully!", HttpStatus.OK);
            }
            return new ResponseEntity<>("Email is exist!", HttpStatus.BAD_REQUEST);
        }
       else{
            return new ResponseEntity<>("Password confirmation is incorrect!", HttpStatus.OK);
        }
    }


    @RequestMapping(value = "/roles", method = RequestMethod.GET)
    public ResponseEntity<List<Role>> getRoles() {
        return new ResponseEntity<>(roleService.findAll(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        accountService.delete(id);
        return ResponseEntity.ok("Delete success!!!");
    }

    @PostMapping("/update")
    public ResponseEntity<String> save(@RequestBody Account account){
        accountService.save(account);
        return ResponseEntity.ok("Update success!!!");
    }

    @PostMapping("/up_role/{id}")
    public ResponseEntity<Void> upRole(@RequestBody Role role,
                                       @PathVariable Long id) {
        Account account = accountService.findById(id);
        account.setRole(role);
        accountService.save(account);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
//    @RequestMapping(value = "/login", method = RequestMethod.POST)
//    public ResponseEntity<?> login(@RequestBody Account user) {
//        Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(user.getName(), user.getPassword()));
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        String jwt = jwtService.generateTokenLogin(authentication);
//        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
//        Account userInfo = accountServiceLogin.findByUsername(user.getName());
//        if (userInfo.isStatus()){
//            return ResponseEntity.ok(new JwtResponse(userInfo.getId_account(), jwt,
//                    userInfo.getName(), userInfo.getName(), userDetails.getAuthorities(), userInfo.getAddressDelivery()));
//        }else {
//            return new ResponseEntity<>("false", HttpStatus.BAD_REQUEST);
//        }
//    }
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> login(@RequestBody Account user) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getName(), user.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtService.generateTokenLogin(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Account userInfo = accountServiceLogin.findByUsername(user.getName());
        if (userInfo.isStatus()) {
            return ResponseEntity.ok(new JwtResponse(userInfo.getId_account(), jwt,
                    userInfo.getName(), userInfo.getFullName(),
                    userDetails.getAuthorities(), userInfo.getAddressDelivery(),
                    userInfo.getImage(), userInfo.getEmail(), userInfo.getPhone()));
        } else {
            return new ResponseEntity<>("false", HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/forget-password")
    public ResponseEntity<Void> forgetPassword(@RequestParam("email") String email) {
        Account account = mailService.findAccountByEmail(email);
        if (account.isStatus()) {
            String to = account.getEmail();
            String subject = "Reset password from Yummy";
            String code = mailService.generateRandomCode();
            String text = "Hello, " + account.getName() + "\n Your password has been reset to " + code + ", please change it. Thank you";
            account.setPassword(code);
            accountService.save(account);
            MailStructure mailStructure = new MailStructure(to,subject,text);
            mailService.sendMail(mailStructure);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    @PostMapping("/change-password")
    public ResponseEntity<Void> changePassword(@RequestBody Account account) {
        if (accountServiceLogin.changePassword(account)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    }

    @GetMapping("/account-by-merchant/{id}")
    public ResponseEntity<AccountDTO> findAccountByMerchant(@PathVariable("id") Long id_merchant){
        return ResponseEntity.ok(accountService.findAccountByMerchant(id_merchant));
    }
    @GetMapping("/{id}")
    public ResponseEntity<Account> findByID(@PathVariable("id") Long id_account){
        return ResponseEntity.ok(accountService.findById(id_account));
    }

}
