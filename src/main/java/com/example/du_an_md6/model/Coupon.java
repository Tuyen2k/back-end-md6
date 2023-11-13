package com.example.du_an_md6.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;

@Entity
@Data
public class Coupon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_account;
    @NotEmpty
    @Column(unique = true)
    private String name;
    private Double discountAmount;
    @Min(value = 1)
    @Max(value = 100)
    private Integer percentageDiscount;
    @Positive
    @Min(value = 1)
    private Long quantity;
}
