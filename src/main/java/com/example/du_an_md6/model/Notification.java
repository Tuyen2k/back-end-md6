package com.example.du_an_md6.model;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_notification;
    @ManyToOne
    @JoinColumn(name = "id_send_acc")
    private Account sendAcc;
    @ManyToOne
    @JoinColumn(name = "id_rec_acc")
    private Account recAcc;
    private String notification;
    @Column(nullable = false)
    @ColumnDefault("false")
    private boolean isWatch;
    private LocalDateTime time;
    private String link;
}
