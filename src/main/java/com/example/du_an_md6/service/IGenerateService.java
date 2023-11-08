package com.example.du_an_md6.service;

import java.util.List;

public interface IGenerateService<E> {
    List<E> findAll();
    E findById(Long id);
    void save(E e);
}
