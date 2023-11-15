package com.example.du_an_md6.mapper;

import com.example.du_an_md6.model.Account;
import com.example.du_an_md6.model.Bill;
import com.example.du_an_md6.model.dto.AccountDTO;
import com.example.du_an_md6.model.dto.BillDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BillMapper implements EntityMapper<BillDTO, Bill> {
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Bill toEntity(BillDTO dto) {
        return modelMapper.map(dto, Bill.class);
    }

    @Override
    public BillDTO toDto(Bill entity) {
        return modelMapper.map(entity, BillDTO.class);
    }

    @Override
    public List<BillDTO> toListDto(List<Bill> entityList) {
        List<BillDTO> billDTOS = new ArrayList<>();
        for(Bill bill : entityList){
            BillDTO billDTO = modelMapper.map(bill, BillDTO.class);
            billDTOS.add(billDTO);
        }
        return billDTOS;
    }
}
