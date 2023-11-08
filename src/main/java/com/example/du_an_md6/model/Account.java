package com.example.du_an_md6.model;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Data
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_account;
    @NotEmpty
    @Column(unique = true)
    private String name;
    @NotEmpty
    @Column(unique = true)
    private String phone;
    private String image;
    @NotEmpty
    private String password;
    @NotEmpty
    private String confirmPassword;
    @Column(nullable = false)
    @ColumnDefault("false")
    private boolean isDelete;
    @ManyToOne
    @JoinColumn(name = "id_role")
    private Role role;
    @ManyToOne
    @JoinColumn(name = "id_address")
    private Address addressDelivery;
}
