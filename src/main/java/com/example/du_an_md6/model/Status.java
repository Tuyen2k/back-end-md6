package com.example.du_an_md6.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Data
public class Status {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_status;
    @NotEmpty
    @Column(unique = true)
    private String name;
}
