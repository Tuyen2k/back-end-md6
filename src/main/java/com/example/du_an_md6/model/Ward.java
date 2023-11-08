package com.example.du_an_md6.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Data
public class Ward {
    @Id
    private Long id_ward;
    private String name;
    @ManyToOne
    @JoinColumn(name = "id_district")
    private District district;
}
