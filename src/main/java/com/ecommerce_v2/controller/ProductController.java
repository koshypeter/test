package com.ecommerce_v2.controller;

import com.ecommerce_v2.Dtos.ProductDto;
import com.ecommerce_v2.model.Category;
import com.ecommerce_v2.model.Product;
import com.ecommerce_v2.repositories.CategoryRepo;
import com.ecommerce_v2.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/product")
public class ProductController {
    @Autowired
    ProductService productService;
    @Autowired
    CategoryRepo categoryRepo;


    @PostMapping("/create")
    public ResponseEntity<?> createproduct(@RequestBody ProductDto productDto) {
        Optional<Category> categoryOptional = categoryRepo.findById(productDto.getCategoryId());
        if (categoryOptional.isPresent()) {
            productService.createproduct(productDto, categoryOptional.get());
        } else {
            return new ResponseEntity<>("Category not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("product added", HttpStatus.OK);
    }

    @GetMapping("/allproducts")
    public ResponseEntity<List<ProductDto>>getallproducts(){
        List<ProductDto>products=productService.getallproducts();
        return new ResponseEntity<>(products,HttpStatus.OK);
    }

    @PutMapping("/update/{productId}")
    public ResponseEntity<?>updateproduct(@PathVariable("productId") Integer productId, @RequestBody ProductDto productDto){
        Optional<Category> categoryOptional = categoryRepo.findById(productDto.getCategoryId());
        if (categoryOptional.isPresent()) {
            productService.updateproduct(productDto, productId);
        } else {
            return new ResponseEntity<>("Category not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Product not found",HttpStatus.NOT_FOUND);
    }
}
