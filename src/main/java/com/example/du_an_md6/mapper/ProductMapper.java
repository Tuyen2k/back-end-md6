package com.example.du_an_md6.mapper;

import com.example.du_an_md6.model.Account;
import com.example.du_an_md6.model.Product;
import com.example.du_an_md6.model.dto.AccountDTO;
import com.example.du_an_md6.model.dto.ProductDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductMapper implements EntityMapper<ProductDTO, Product>{
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public Product toEntity(ProductDTO productDTO) {
        return modelMapper.map(productDTO, Product.class);
    }

    @Override
    public ProductDTO toDto(Product product) {
        return modelMapper.map(product, ProductDTO.class);
    }

    @Override
    public List<ProductDTO> toListDto(List<Product> products) {
        List<ProductDTO> productsDTO = new ArrayList<>();
        for(Product product : products){
            ProductDTO productDTO = modelMapper.map(product, ProductDTO.class);
            productsDTO.add(productDTO);
        }
        return productsDTO;
    }
}
