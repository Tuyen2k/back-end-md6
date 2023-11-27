package com.example.du_an_md6.controller;

import com.example.du_an_md6.model.Product;
import com.example.du_an_md6.model.dto.ProductDTO;
import com.example.du_an_md6.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    private IProductService productService ;
    @GetMapping("{id_merchant}")
    public ResponseEntity<List<Product>> findProductMerchant (@PathVariable Long id_merchant){
        return new ResponseEntity<>(productService.findProductMerchant(id_merchant), HttpStatus.OK);
    }

    @GetMapping("/purchase/{id_merchant}")
    public ResponseEntity<List<Product>> findProductPurchaseMerchant (@PathVariable Long id_merchant){
        List<Product> p = productService.findProductMerchant(id_merchant);
        List<Product> productList = new ArrayList<>(p);
        for (int i = 0; i < productList.size(); i++) {
            for (int j = i + 1; j < productList.size(); j++) {
                if (productList.get(i).getPurchase() < productList.get(j).getPurchase()) {
                    Collections.swap(productList, i, j);
                }
            }
        }
        return new ResponseEntity<>(productList, HttpStatus.OK);
    }
    @GetMapping("/merchant/{id_product}")
    public ResponseEntity<ProductDTO> findById (@PathVariable Long id_product){
        ProductDTO productDTO = productService.getProductDTO(id_product);
        if (productDTO != null){
            return ResponseEntity.ok(productDTO);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<ProductDTO>> findAllProduct() {
        return new ResponseEntity<>(productService.getAll(), HttpStatus.OK);
    }
    @GetMapping("/purchased")
    public ResponseEntity<List<ProductDTO>> MostPurchasedProducts() {
        return new ResponseEntity<>(productService.MostPurchasedProducts(), HttpStatus.OK);
    }

    @GetMapping("/findOne/{id}")
    public ResponseEntity<Product> findOne(@PathVariable Long id) {
        return new ResponseEntity<>(productService.findById(id), HttpStatus.OK);
    }

    @GetMapping("/search")
    public List<Product> searchProductForMerchant(@RequestParam("id_merchant") Long id_merchant,
                                        @RequestParam("name") String name) {
        return productService.findAllByMerchantAndNameProduct(id_merchant, name);
    }
    @PostMapping()
    public ResponseEntity<String> save(@RequestBody Product product){
        productService.save(product);
        return ResponseEntity.ok("Create success!!!");
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        Product product = productService.findById(id);
        product.setDelete(true);
        productService.save(product);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("/search/{id}")
    public ResponseEntity<List<Product>> searchByCategory(@PathVariable Long id){
        if (!productService.findProductsByCategory(id).isEmpty()){
            return new ResponseEntity<>(productService.findProductsByCategory(id) ,HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("fill_by_price")
    public ResponseEntity<List<ProductDTO>> searchByCategory(){
        if (!productService.findAllProductsByPriceSale().isEmpty()){
            return new ResponseEntity<>(productService.findAllProductsByPriceSale(),HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
