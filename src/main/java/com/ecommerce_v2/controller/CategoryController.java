package com.ecommerce_v2.controller;

import com.ecommerce_v2.model.Category;
import com.ecommerce_v2.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @PostMapping(path = "/create")
    public ResponseEntity<?> createcategorymethod(@RequestBody Category category){
        try{
            return categoryService.createcategory(category);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Failed", HttpStatus.BAD_REQUEST);
    }
    //update category
    @PutMapping(path = "/updatecategory")
    public ResponseEntity<?>updatecategory(@RequestParam Integer id,@RequestBody Category category) {
        try{
            return categoryService.updatecategory(id,category);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Error",HttpStatus.BAD_REQUEST);
    }
    //get all categories
    @GetMapping(path = "/getallcategory")
    public ResponseEntity<List<Category>>getallcategory(){
        try{
            return categoryService.getallcategory();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.INTERNAL_SERVER_ERROR);
    }
    //get category by id
    @GetMapping(path = "/getcategorybyid")
    public ResponseEntity<?>getcategorybyid(@RequestParam Integer id){
        try{
            return categoryService.getcategorybyid(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Error",HttpStatus.INTERNAL_SERVER_ERROR);
    }
    //get category by name
    @GetMapping(path = "/getcategorybyname")
    public ResponseEntity<?>getcategorybyname(@RequestParam String cName) {
        try {
            return categoryService.getcategorybyname(cName);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Error",HttpStatus.INTERNAL_SERVER_ERROR);
    }
    //delete category by id
    @DeleteMapping(path = "/deletecategory")
    public ResponseEntity<?>deletecategorybyid(@RequestParam Integer id){
        try{
            return categoryService.deletecategory(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Invalid id",HttpStatus.NOT_FOUND);
    }

}





