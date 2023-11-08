package com.example.du_an_md6.mapper;

import com.example.du_an_md6.model.Ward;
import com.example.du_an_md6.model.Merchant;
import com.example.du_an_md6.model.dto.WardDTO;
import com.example.du_an_md6.model.dto.MerchantDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class WardMapper implements EntityMapper<WardDTO, Ward>{
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public Ward toEntity(WardDTO dto) {
        return modelMapper.map(dto, Ward.class);
    }

    @Override
    public WardDTO toDto(Ward entity) {
        return modelMapper.map(entity, WardDTO.class);
    }

    @Override
    public List<WardDTO> toListDto(List<Ward> entityList) {
        List<WardDTO> WardsDTO = new ArrayList<>();
        for(Ward Ward : entityList){
            WardDTO WardDTO = modelMapper.map(Ward, WardDTO.class);
            WardsDTO.add(WardDTO);
        }
        return WardsDTO;
    }
}
