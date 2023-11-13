package com.example.du_an_md6.model.dto;

import com.example.du_an_md6.model.Address;
import com.example.du_an_md6.model.Cart;
import com.example.du_an_md6.model.Product;
import lombok.Data;

import javax.persistence.*;

@Data
public class CartDetailDTO {

    private Long id_cartDetail;

    private CartDTO cart;

    private ProductDTO product;
    private int quantity;
    private double price;

    private AddressDTO address;
}
