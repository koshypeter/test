package com.ecommerce_v2.service;

import com.ecommerce_v2.model.Category;
import com.ecommerce_v2.repositories.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    CategoryRepo categoryRepo;

    //add category
    public ResponseEntity<?> createcategory(Category category) {
        Category category1=new Category();
        category1.setId(category.getId());
        category1.setcDesc(category.getcDesc());
        category1.setcName(category.getcName());
        category1.setcImageUrl(category.getcImageUrl());
        categoryRepo.save(category1);
        return new ResponseEntity<>(category1, HttpStatus.OK);
    }
    //update category
    public ResponseEntity<?> updatecategory(Integer id, Category category)  {
        Optional<Category> categoryModelOptional=categoryRepo.findById(id);
        if (categoryModelOptional.isPresent()){
            Category categoryModel1=categoryModelOptional.get();
            categoryModel1.setcDesc(category.getcDesc());
            categoryModel1.setcName(category.getcName());
            categoryModel1.setcImageUrl(category.getcImageUrl());
            categoryRepo.save(categoryModel1);
            return new ResponseEntity<>(categoryModel1,HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Invalid id",HttpStatus.NOT_FOUND);
        }
    }
    //get all categories
    public ResponseEntity<List<Category>> getallcategory() {
        List<Category>categoryModelList=categoryRepo.findAll();
        return new ResponseEntity<>(categoryModelList,HttpStatus.OK);
    }
    //get category by id
    public ResponseEntity<?> getcategorybyid(Integer id) {
        Optional<Category> categoryModelOptional=categoryRepo.findById(id);
        if (categoryModelOptional.isPresent()){
            Category categoryModel=categoryModelOptional.get();
            return new ResponseEntity<>(categoryModel,HttpStatus.OK);
        }
        else
            return new ResponseEntity<>("Invalid id",HttpStatus.NOT_FOUND);
    }
    //get category by name
    public ResponseEntity<?> getcategorybyname(String cName) {
        Optional<Category> categoryOptional = categoryRepo.findBycName(cName);
        if (categoryOptional.isPresent()) {
            Category category = categoryOptional.get();
            return new ResponseEntity<>(category, HttpStatus.OK);
        }
        else
            return new ResponseEntity<>("Such category does not exist",HttpStatus.NOT_FOUND);
    }
    //delete category
    public ResponseEntity<?> deletecategory(Integer id) {
        Optional<Category> categoryModelOptional=categoryRepo.findById(id);
        if (categoryModelOptional.isPresent()){
            Category categoryModel=categoryModelOptional.get();
            categoryRepo.delete(categoryModel);
            return new ResponseEntity<>("DELETED",HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Invalid id",HttpStatus.NOT_FOUND);
        }
    }
}
