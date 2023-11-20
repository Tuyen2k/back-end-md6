package com.example.du_an_md6.service;

import com.example.du_an_md6.model.Message;

import java.util.List;

public interface IMessageService extends IGenerateService<Message> {
    List<Message> findAllByAccount(Long id_send, Long id_rec);
}
