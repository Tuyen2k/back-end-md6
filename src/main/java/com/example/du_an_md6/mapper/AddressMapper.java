package com.example.du_an_md6.mapper;

import com.example.du_an_md6.model.Account;
import com.example.du_an_md6.model.Address;
import com.example.du_an_md6.model.dto.AccountDTO;
import com.example.du_an_md6.model.dto.AddressDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class AddressMapper implements EntityMapper<AddressDTO, Address> {
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public Address toEntity(AddressDTO addressDTO) {
        return modelMapper.map(addressDTO, Address.class);
    }

    @Override
    public AddressDTO toDto(Address address) {
        return modelMapper.map(address, AddressDTO.class);
    }

    @Override
    public List<AddressDTO> toListDto(List<Address> addresses) {
        List<AddressDTO> addressList = new ArrayList<>();
        for(Address address : addresses){
            AddressDTO addressDTO = modelMapper.map(address, AddressDTO.class);
            addressList.add(addressDTO);
        }
        return addressList;
    }
}
