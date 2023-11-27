package com.example.du_an_md6.service;

import com.example.du_an_md6.model.Product;
import com.example.du_an_md6.model.dto.ProductDTO;
import com.example.du_an_md6.service.IGenerateService;

import java.util.List;

public interface IProductService extends IGenerateService<Product> {
    List<Product> findProductMerchant(Long id_merchant);

    ProductDTO getProductDTO(Long id_product);
    List<Product> findAllByMerchantAndNameProduct(Long id_merchant, String name);
    //    List<Product> filterProduct (String name, Long id_category);
    List<ProductDTO> getAll();
    List<Product> findProductsByCategory(Long id_category);
    List<ProductDTO> MostPurchasedProducts();
    List<ProductDTO> findAllProductsByPriceSale();

    void updatePurchase(Long id_product, int quantity);
}
