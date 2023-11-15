package com.example.du_an_md6.mapper;

import com.example.du_an_md6.model.Bill;
import com.example.du_an_md6.model.BillDetail;
import com.example.du_an_md6.model.dto.BillDTO;
import com.example.du_an_md6.model.dto.BillDetailDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BillDetailMapper implements EntityMapper<BillDetailDTO, BillDetail> {
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public BillDetail toEntity(BillDetailDTO dto) {
        return modelMapper.map(dto, BillDetail.class);
    }

    @Override
    public BillDetailDTO toDto(BillDetail entity) {
        return modelMapper.map(entity, BillDetailDTO.class);
    }

    @Override
    public List<BillDetailDTO> toListDto(List<BillDetail> entityList) {
        List<BillDetailDTO> billDetailDTOS = new ArrayList<>();
        for(BillDetail bill : entityList){
            BillDetailDTO billDetailDTO = modelMapper.map(bill, BillDetailDTO.class);
            billDetailDTOS.add(billDetailDTO);
        }
        return billDetailDTOS;
    }
}
