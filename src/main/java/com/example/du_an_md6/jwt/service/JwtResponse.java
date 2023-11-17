package com.example.du_an_md6.jwt.service;

import com.example.du_an_md6.model.Address;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Getter
@Setter
public class JwtResponse {

    private Long id;
    private String token;
    private String type = "Bearer";
    private String username;
    private String name;
    private final Collection<? extends GrantedAuthority> authorities;
    private Address address;
    private String image;
    private String email;
    private String phone;

    public JwtResponse(Long id, String token, String username, String name, Collection<? extends GrantedAuthority> authorities, Address address) {
        this.id = id;
        this.token = token;
        this.username = username;
        this.name = name;
        this.authorities = authorities;
        this.address = address;
    }

    public JwtResponse(Long id, String token, String username, String name, Collection<? extends GrantedAuthority> authorities, Address address, String image, String email, String phone) {
        this.id = id;
        this.token = token;
        this.type = type;
        this.username = username;
        this.name = name;
        this.authorities = authorities;
        this.address = address;
        this.image = image;
        this.email = email;
        this.phone = phone;
    }
}
