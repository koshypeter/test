package com.ecommerce_v2.controller;

import com.ecommerce_v2.Dtos.ProductDto;
import com.ecommerce_v2.common.ApiResponse;
import com.ecommerce_v2.model.Product;
import com.ecommerce_v2.model.User;
import com.ecommerce_v2.model.Wishlist;
import com.ecommerce_v2.service.AuthService;
import com.ecommerce_v2.service.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/wishlist")
public class WishlistController {
    @Autowired
    WishlistService wishlistService;
    @Autowired
    AuthService authService;

    //save product to wishlist
    @PostMapping("/addtowishlist")
    public ResponseEntity<?> addToWishlist(@RequestBody Product product, @RequestParam("token") String token){
        authService.authenticate(token);//authenticate the token
        User user = authService.getUser(token);//get user details

        Wishlist wishlist=new Wishlist(user,product);
        wishlistService.createWishlist(wishlist);
        return new ResponseEntity<>("Added to wishlist", HttpStatus.OK);
    }

    //display the items in the wishlist
    @GetMapping("/{token}")
    public ResponseEntity<List<ProductDto>> getWishList(@PathVariable("token") String token){
        authService.authenticate(token);//authenticate the token
        User user = authService.getUser(token);//get user details
        List<ProductDto> productDtos=wishlistService.getUserWishList(user);
        return new ResponseEntity<>(productDtos,HttpStatus.OK);
    }

}
