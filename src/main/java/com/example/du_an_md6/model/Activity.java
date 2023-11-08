package com.example.du_an_md6.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_activity;
    @Column(unique = true)
    private String name;
}
