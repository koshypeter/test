package com.ecommerce_v2.service;

import com.ecommerce_v2.Dtos.ProductDto;
import com.ecommerce_v2.model.Category;
import com.ecommerce_v2.model.Product;
import com.ecommerce_v2.repositories.CategoryRepo;
import com.ecommerce_v2.repositories.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    ProductRepo productRepo;
    @Autowired
    CategoryRepo categoryRepo;

    public ResponseEntity<?> createproduct(ProductDto productDto, Category category) {
        Product product=new Product();
        product.setPdesc(productDto.getPdesc());
        product.setPimgurl(productDto.getPimgurl());
        product.setPname(productDto.getPname());
        product.setPrice(productDto.getPrice());
        product.setCategory(category);
        productRepo.save(product);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    public ProductDto getProductDto(Product product){
        ProductDto productDto=new ProductDto();
        productDto.setProductId(product.getPid());
        productDto.setPdesc(product.getPdesc());
        productDto.setPimgurl(product.getPimgurl());
        productDto.setPname(product.getPname());
        productDto.setPrice(product.getPrice());
        productDto.setCategoryId(product.getCategory().getId());
        return productDto;
    }

    public List<ProductDto>getallproducts() {
        List<Product> allProducts= productRepo.findAll();

        List<ProductDto> productDtos = new ArrayList<>();
        for(Product product:allProducts){
            productDtos.add(getProductDto(product));
        }
        return productDtos;
    }

    public ResponseEntity<String> updateproduct(ProductDto productDto, Integer productId) {
        Optional<Product> productOptional=productRepo.findById(productId);
        try {
            if (!productOptional.isPresent()) {
                return new ResponseEntity<>("product does not exist", HttpStatus.NOT_FOUND);
            } else {
                Product product = productOptional.get();
                product.setPdesc(productDto.getPdesc());
                product.setPimgurl(productDto.getPimgurl());
                product.setPname(productDto.getPname());
                product.setPrice(productDto.getPrice());
                productRepo.save(product);
                return new ResponseEntity<>("Product updated succesfully", HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
