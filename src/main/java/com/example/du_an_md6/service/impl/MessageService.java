package com.example.du_an_md6.service.impl;

import com.example.du_an_md6.model.Message;
import com.example.du_an_md6.repository.IMessageRepository;
import com.example.du_an_md6.service.IMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService implements IMessageService {

    @Autowired
    private IMessageRepository iMessageRepository;

    @Override
    public List<Message> findAll() {
        return iMessageRepository.findAll();
    }

    @Override
    public Message findById(Long id) {
        return iMessageRepository.findById(id).orElse(null);
    }

    @Override
    public void save(Message message) {
        iMessageRepository.save(message);
    }

    @Override
    public List<Message> findAllByAccount(Long id_send, Long id_rec) {
        return iMessageRepository.findAllByAccount(id_send, id_rec);
    }
}
