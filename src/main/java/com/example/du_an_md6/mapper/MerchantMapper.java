package com.example.du_an_md6.mapper;
import com.example.du_an_md6.model.Merchant;
import com.example.du_an_md6.model.dto.MerchantDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class MerchantMapper implements EntityMapper<MerchantDTO, Merchant> {
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public Merchant toEntity(MerchantDTO dto) {
        return modelMapper.map(dto, Merchant.class);
    }
    @Override
    public MerchantDTO toDto(Merchant entity) {
        return modelMapper.map(entity, MerchantDTO.class);
    }
    @Override
    public List<MerchantDTO> toListDto(List<Merchant> entityList) {
        List<MerchantDTO> merchantsDTO = new ArrayList<>();
        for(Merchant merchant : entityList){
            MerchantDTO merchantDTO = modelMapper.map(merchant, MerchantDTO.class);
            merchantsDTO.add(merchantDTO);
        }
        return merchantsDTO;
    }
}
