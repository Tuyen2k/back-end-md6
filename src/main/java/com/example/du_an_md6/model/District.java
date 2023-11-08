package com.example.du_an_md6.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class District {
    @Id
    private Long id_district;
    @Column(unique = true)
    private String name;
    @ManyToOne
    @JoinColumn(name = "id_city")
    private City city;

}
