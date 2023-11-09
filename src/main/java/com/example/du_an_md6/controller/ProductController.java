package com.example.du_an_md6.controller;
import com.example.du_an_md6.model.Product;
import com.example.du_an_md6.model.dto.ProductDTO;
import com.example.du_an_md6.service.IProductService;
import com.example.du_an_md6.service.impl.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    private IProductService productService ;
    @Autowired
    private ProductService productS;
    @GetMapping("{id_merchant}")
    public ResponseEntity<List<Product>> findProductMerchant (@PathVariable Long id_merchant){
        return new ResponseEntity<>(productService.findProductMerchant(id_merchant), HttpStatus.OK);
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
    public ResponseEntity<List<Product>> findAllProduct() {
        List<Product> productList = productService.findAll();
        return new ResponseEntity<>(productList, HttpStatus.OK);
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
        if (!productS.findProductsByCategory(id).isEmpty()){
            return new ResponseEntity<>(productS.findProductsByCategory(id) ,HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
