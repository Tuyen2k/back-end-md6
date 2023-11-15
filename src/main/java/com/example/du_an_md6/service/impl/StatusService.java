package com.example.du_an_md6.service.impl;

import com.example.du_an_md6.model.Status;
import com.example.du_an_md6.repository.IStatusRepository;
import com.example.du_an_md6.service.IStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatusService implements IStatusService {
    @Autowired
    private IStatusRepository iStatusRepository;
    @Override
    public List<Status> findAll() {
        return iStatusRepository.findAll();
    }

    @Override
    public Status findById(Long id) {
        return iStatusRepository.findById(id).orElse(null);
    }

    @Override
    public void save(Status status) {
        iStatusRepository.save(status);
    }
}
