package com.example.du_an_md6.service.impl;

import com.example.du_an_md6.model.Account;
import com.example.du_an_md6.model.Notification;
import com.example.du_an_md6.repository.INotificationRepository;
import com.example.du_an_md6.service.INotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService implements INotificationService {
    @Autowired
    private INotificationRepository iNotificationRepository;
    @Override
    public List<Notification> findAll() {
        return iNotificationRepository.findAll();
    }

    @Override
    public Notification findById(Long id) {
        return iNotificationRepository.findById(id).orElse(null);
    }

    @Override
    public void save(Notification notification) {
        iNotificationRepository.save(notification);
    }

    @Override
    public List<Notification> getAllByRecAcc(Account recAcc) {
        return iNotificationRepository.findAllByRecAcc(recAcc.getId_account());
    }
}
