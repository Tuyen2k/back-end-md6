package com.example.du_an_md6.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Account sendAcc;
    @ManyToOne
    private Account receiverAcc;
    private LocalDateTime time;
    private String message;
}
