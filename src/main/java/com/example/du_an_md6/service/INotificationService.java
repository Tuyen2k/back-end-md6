package com.example.du_an_md6.service;

import com.example.du_an_md6.model.Account;
import com.example.du_an_md6.model.Notification;

import java.util.List;

public interface INotificationService extends IGenerateService<Notification>{

    List<Notification> getAllByRecAcc(Account recAcc);
}
