package com.example.du_an_md6.model.dto;

import lombok.Data;

@Data
public class AddressDTO {

    private Long id_address;
    private CityDTO city;
    private DistrictDTO district;
    private WardDTO ward;
    private String address_detail;
}
