package com.example.du_an_md6.service.impl;

import com.example.du_an_md6.mapper.ProductMapper;
import com.example.du_an_md6.model.Category;
import com.example.du_an_md6.model.Product;
import com.example.du_an_md6.model.dto.ProductDTO;
import com.example.du_an_md6.repository.IProductRepository;
import com.example.du_an_md6.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService implements IProductService {
    @Autowired
    private IProductRepository productRepository;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private CategoryService categoryService;

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product findById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public ProductDTO getProductDTO(Long id_product) {
        return productMapper.toDto(productRepository.findById(id_product).orElse(null));
    }

    @Override
    public void save(Product product) {
        productRepository.save(product);
    }

    @Override
    public List<Product> findProductMerchant(Long id_merchant) {
        return productRepository.findProductMerchant(id_merchant);
    }

    public List<Product> findProductsByCategory(Long id_category){
        return productRepository.findProductByCategory(id_category);
    }

}

