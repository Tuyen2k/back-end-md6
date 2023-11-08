package com.example.du_an_md6.controller;

import com.example.du_an_md6.mapper.DistrictMapper;
import com.example.du_an_md6.mapper.WardMapper;
import com.example.du_an_md6.model.City;
import com.example.du_an_md6.model.District;
import com.example.du_an_md6.model.Ward;
import com.example.du_an_md6.model.dto.DistrictDTO;
import com.example.du_an_md6.model.dto.WardDTO;
import com.example.du_an_md6.service.impl.AddressServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/address")
public class AddressController {
    @Autowired
    private AddressServiceImpl addressService;
    @Autowired
    private DistrictMapper districtMapper;
    @Autowired
    private WardMapper wardMapper;
    @GetMapping
    public ResponseEntity<List<City>> findAllCity(){
        return ResponseEntity.ok(addressService.findAllCity());
    }
    @GetMapping("/city/{id}")
    public ResponseEntity<City> findCityById(@PathVariable("id") Long id_city){
        return ResponseEntity.ok(addressService.findCityById(id_city));
    }
    @GetMapping("/district/{id_city}")
    public ResponseEntity<List<DistrictDTO>> findAllDistrictByCity(@PathVariable("id_city") Long id_city){
        List<District> districts = addressService.findAllDistrictByCity(id_city);
        return ResponseEntity.ok(districtMapper.toListDto(districts));
    }
//    @GetMapping("/{id}")
//    public ResponseEntity<District> findDistrictById(@PathVariable("id") Long id_district){
//        return ResponseEntity.ok(addressService.findDistrictById(id_district));
//    }

    @GetMapping("/ward/{id_district}")
    public ResponseEntity<List<WardDTO>> findAllWardByDistrict(@PathVariable("id_district") Long id_district){
        List<Ward> wardList = addressService.findAlWardByDistrict(id_district);
        return ResponseEntity.ok(wardMapper.toListDto(wardList));
    }
    @GetMapping("/{id}")
    public ResponseEntity<Ward> findWardById(@PathVariable("id") Long id_ward){
        return ResponseEntity.ok(addressService.findWardById(id_ward));
    }

}
