package com.example.du_an_md6.mapper;

import com.example.du_an_md6.model.District;
import com.example.du_an_md6.model.Merchant;
import com.example.du_an_md6.model.dto.DistrictDTO;
import com.example.du_an_md6.model.dto.MerchantDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class DistrictMapper implements EntityMapper<DistrictDTO, District>{
   @Autowired
    private ModelMapper modelMapper;
    @Override
    public District toEntity(DistrictDTO dto) {
            return modelMapper.map(dto, District.class);
    }

    @Override
    public DistrictDTO toDto(District entity) {
            return modelMapper.map(entity, DistrictDTO.class);
    }

    @Override
    public List<DistrictDTO> toListDto(List<District> entityList) {
        List<DistrictDTO> districtsDTO = new ArrayList<>();
        for(District district : entityList){
            DistrictDTO districtDTO = modelMapper.map(district, DistrictDTO.class);
            districtsDTO.add(districtDTO);
        }
        return districtsDTO;
    }
}
