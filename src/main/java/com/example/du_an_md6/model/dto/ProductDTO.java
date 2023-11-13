package com.example.du_an_md6.model.dto;

import com.example.du_an_md6.model.Category;
import com.example.du_an_md6.model.Merchant;
import lombok.Data;

import java.util.List;

@Data
public class ProductDTO {

    private Long id_product;
    private MerchantDTO merchant;
    private String name;
    private double price;
    private double priceSale;
    private String image;
    private String description;
    private boolean isDelete;
    private List<Category> categories;
    private int view;
    private int purchase;
    private boolean status;
    private String timeMake;
}
