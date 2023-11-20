package com.example.du_an_md6.controller;

import com.example.du_an_md6.model.Message;
import com.example.du_an_md6.service.IMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/websocket")
public class MessageController {

    @Autowired
    private IMessageService iMessageService;

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
}
