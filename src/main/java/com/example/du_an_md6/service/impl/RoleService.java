package com.example.du_an_md6.service.impl;


import com.example.du_an_md6.model.Role;
import com.example.du_an_md6.repository.IRoleRepository;
import com.example.du_an_md6.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService implements IRoleService {
    @Autowired
    private IRoleRepository iRoleRepository;

    @Override
    public Role findById(Long id) {
        return iRoleRepository.findById(id).orElse(null);
    }

    @Override
    public List<Role> findAll() {
        return iRoleRepository.findAll();
    }

    @Override
    public void save(Role role) {
        iRoleRepository.save(role);
    }
}
