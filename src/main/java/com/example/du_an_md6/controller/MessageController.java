package com.example.du_an_md6.controller;

import com.example.du_an_md6.model.Account;
import com.example.du_an_md6.model.Message;
import com.example.du_an_md6.model.Notification;
import com.example.du_an_md6.service.IMessageService;
import com.example.du_an_md6.service.INotificationService;
import com.example.du_an_md6.service.impl.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/websocket")
public class MessageController {

    @Autowired
    private IMessageService iMessageService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private INotificationService iNotificationService;

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/message")
    @SendTo("/chatroom/public")
    public Message receiveMessage(@Payload Message message){
        return message;
    }

    @MessageMapping("/private-message")
    public Message recMessage(@Payload Message message){
        iMessageService.save(message);
        String user = message.getReceiverAcc().getName() + message.getReceiverAcc().getId_account();
        simpMessagingTemplate.convertAndSendToUser(user,"/private",message);
        return message;
    }

    @MessageMapping("/private-notification")
    public Notification recNotification(@Payload Notification notification){
        notification.setTime(LocalDateTime.now());
        iNotificationService.save(notification);
        String user = notification.getRecAcc().getName() + notification.getRecAcc().getId_account();
        simpMessagingTemplate.convertAndSendToUser(user, "/private-notification", notification);
        return notification;
    }

    @GetMapping("/notification/account/{id}")
    public ResponseEntity<List<Notification>> getNotificationByAccount(@PathVariable("id") Long id_account){
        Account account = accountService.findById(id_account);
        return ResponseEntity.ok(iNotificationService.getAllByRecAcc(account));
    }

    @GetMapping("/notification/{id}")
    public ResponseEntity<Void> updateWatchNotification(@PathVariable("id") Long id_notification){
        Notification notification = iNotificationService.findById(id_notification);
        if (!Objects.equals(notification, null)){
            notification.setWatch(true);
            iNotificationService.save(notification);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
