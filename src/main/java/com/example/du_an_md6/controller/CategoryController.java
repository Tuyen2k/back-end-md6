package com.example.du_an_md6.controller;

import com.example.du_an_md6.model.Category;
import com.example.du_an_md6.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/categories")
public class CategoryController {
    @Autowired
    private ICategoryService iCategoryService;

    @GetMapping
    public ResponseEntity<List<Category>> getAllCategory() {
        return new ResponseEntity<>(iCategoryService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody Category category) {
        iCategoryService.save(category);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/update")
    public ResponseEntity<Void> updateCategory(@RequestBody Category category) {
        iCategoryService.save(category);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> findById(@PathVariable Long id) {
        return ResponseEntity.ok(iCategoryService.findById(id));
    }
}
