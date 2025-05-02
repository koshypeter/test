package com.ecommerce_v2.service;

import com.ecommerce_v2.Dtos.ProductDto;
import com.ecommerce_v2.model.User;
import com.ecommerce_v2.model.Wishlist;
import com.ecommerce_v2.repositories.WishlistRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WishlistService {
    @Autowired
    WishlistRepo wishlistRepo;
    @Autowired
    ProductService productService;

    public void createWishlist(Wishlist wishlist) {
        wishlistRepo.save(wishlist);
    }

    public List<ProductDto> getUserWishList(User user) {
        final List<Wishlist> wishlists=wishlistRepo.findAllByUserOrderByCreateddateDesc(user);
        List<ProductDto> productDtos=new ArrayList<>();
        for(Wishlist wishlist:wishlists){
            productDtos.add(productService.getProductDto(wishlist.getProduct()));
        }
        return productDtos;
    }
}
