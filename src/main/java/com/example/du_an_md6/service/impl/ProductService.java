package com.example.du_an_md6.service.impl;

import com.example.du_an_md6.mapper.ProductMapper;
import com.example.du_an_md6.model.Product;
import com.example.du_an_md6.model.dto.ProductDTO;
import com.example.du_an_md6.repository.IProductRepository;
import com.example.du_an_md6.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService implements IProductService {
    @Autowired
    private IProductRepository productRepository ;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private CategoryService categoryService;

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }
    @Override
    public List<ProductDTO> getAll(){
        return productMapper.toListDto(productRepository.findAllByActivity());
    }

    @Override
    public Product findById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public ProductDTO getProductDTO(Long id_product) {
        return productMapper.toDto(productRepository.findByIdActivity(id_product).orElse(null));
    }

    @Override
    public void save(Product product) {
        productRepository.save(product);
    }

    @Override
    public List<Product> findProductMerchant(Long id_merchant) {
        return productRepository.findProductMerchant(id_merchant) ;
    }

    public List<ProductDTO> MostPurchasedProducts() {
        List<ProductDTO> p = getAll();
        List<ProductDTO> productDTOList = new ArrayList<>(p);
        for (int i = 0; i < productDTOList.size(); i++) {
            for (int j = i + 1; j < productDTOList.size(); j++) {
                if (productDTOList.get(i).getPurchase() < productDTOList.get(j).getPurchase()) {
                    Collections.swap(productDTOList, i, j);
                }
            }
        }
        return productDTOList;
    }

    public List<ProductDTO> findAllProductsByPriceSale() {
        List<ProductDTO> p = getAll();

        List<ProductDTO> top10Products = p.stream()
                .sorted(Comparator.comparing(ProductDTO::getPrice).reversed())
                .limit(10)
                .collect(Collectors.toList());

         return top10Products;
    }


//        public List<ProductDTO> findAllProductsByPriceSale() {
//        List<ProductDTO> p = getAll();
//        List<Double> count = new ArrayList<>();
//        List<ProductDTO> productDTOList = new ArrayList<>();
//        for (ProductDTO productDTO : p) {
//            Double total = productDTO.getPrice() - productDTO.getPriceSale();
//            count.add(total);
//        }
//        List<Double> sortedList = count.stream()
//                .sorted(Collections.reverseOrder())
//                .collect(Collectors.toList());
//        List<Double> top10Values = sortedList.subList(0, Math.min(10, sortedList.size()));
//        for (ProductDTO productDTO : p) {
//            for (Double check : top10Values) {
//                if (productDTO.getPrice() - productDTO.getPriceSale() == check) {
//                    productDTOList.add(productDTO);
//                }
//            }
//        }
//        return productDTOList;
//    }
    @Override
    public List<Product> findProductsByCategory(Long id_category){
        return productRepository.findProductByCategory(id_category);
    }
    @Transactional(readOnly = true)
    public List<Product> findAllByMerchantAndNameProduct(Long id_merchant, String name) {
        return productRepository.findAllByMerchantAndNameProduct(id_merchant, "%" + name + "%");
    }

    @Override
    public void updatePurchase(Long id_product, int quantity) {
        Product product = productRepository.findById(id_product).orElse(null);
        if (product != null) {
            int currentPurchase = product.getPurchase();
            int newPurchase = currentPurchase + quantity;
            product.setPurchase(newPurchase);
            productRepository.save(product);
        }
    }

}
