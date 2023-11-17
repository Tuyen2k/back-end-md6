package com.example.du_an_md6.service.impl;

import com.example.du_an_md6.mapper.MerchantMapper;
import com.example.du_an_md6.model.Merchant;
import com.example.du_an_md6.model.dto.MerchantDTO;
import com.example.du_an_md6.repository.IMerchantRepository;
import com.example.du_an_md6.service.IMerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service

public class MerchantService implements IMerchantService {
    @Autowired
    IMerchantRepository merchantRepository;
    @Autowired
    MerchantMapper merchantMapper;

    @Override
    public List<Merchant> findAll() {
        return merchantRepository.findAll();
    }

    @Override
    public List<MerchantDTO> findAllCheckDelete(){
        List<Merchant> merchants = merchantRepository.findAll();
        List<Merchant> merchantList = new ArrayList<>();
        for (Merchant m: merchants) {
            if (!m.isDelete() && m.getActivity().getId_activity() == 3){
                merchantList.add(m);
            }
        }
        return merchantMapper.toListDto(merchantList);
    }

    @Override
    public Merchant findById(Long id) {
        if (merchantRepository.findById(id).isPresent()) {
            return merchantRepository.findById(id).get();
        } else {
            return null;
        }
    }

    @Override
    public void save(Merchant merchant) {
        merchantRepository.save(merchant);
    }


    public Merchant findMerchantByIdAcc(Long id_account) {
        Merchant merchant = null;
        List<Merchant> merchants = findAll();
        for (Merchant m : merchants) {
            if (m.getAccount().getId_account().equals(id_account)) {
                return merchant = m;
            }
        }
        return merchant;
    }

    @Override
    public MerchantDTO findByAccount(Long id_account) {
        return merchantMapper.toDto(merchantRepository.findByAccount(id_account).orElse(null));
    }
}
