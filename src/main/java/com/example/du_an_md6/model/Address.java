package com.example.du_an_md6.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_address;
    @ManyToOne
    @JoinColumn(name = "id_city")
    private City city;
    @ManyToOne
    @JoinColumn(name = "id_district")
    private District district;
    @ManyToOne
    @JoinColumn(name = "id_ward")
    private Ward ward;
    private String address_detail;
}
