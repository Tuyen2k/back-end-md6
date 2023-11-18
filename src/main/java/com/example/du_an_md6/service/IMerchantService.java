package com.example.du_an_md6.service;

import com.example.du_an_md6.model.Merchant;
import com.example.du_an_md6.model.dto.MerchantDTO;

import java.util.List;

public interface IMerchantService extends IGenerateService<Merchant>{
    List<MerchantDTO> findAllCheckDelete();


    MerchantDTO findByAccount(Long id_account);
}
