package com.ecommerce_v2.controller;

import com.ecommerce_v2.Dtos.ProductDto;
import com.ecommerce_v2.Dtos.cart.AddToCartDto;
import com.ecommerce_v2.Dtos.cart.CartDto;
import com.ecommerce_v2.model.Cart;
import com.ecommerce_v2.model.Product;
import com.ecommerce_v2.model.User;
import com.ecommerce_v2.service.AuthService;
import com.ecommerce_v2.service.CartService;
import com.ecommerce_v2.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private CartService cartService;
    @Autowired
    private AuthService authService;

    @PostMapping("/addtocart")
    public ResponseEntity<?> addtocart(@RequestBody AddToCartDto addToCartDto, @RequestParam("token") String token) {
        authService.authenticate(token);//authenticate the token
        User user = authService.getUser(token);//get user details

        cartService.addtocart(addToCartDto,user);
        return new ResponseEntity<>("Added to cart", HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<CartDto> getCartItems(@RequestParam("token") String token){
        authService.authenticate(token);//authenticate the token
        User user = authService.getUser(token);//get user details

        CartDto cartDto= cartService.listCartItems(user);
        return new ResponseEntity<>(cartDto,HttpStatus.OK);
    }

    @DeleteMapping("/delete/{cartItemId}")
    public ResponseEntity<?> deleteCartItem(@PathVariable("cartItemId") Integer cartItemId, @RequestParam("token") String token){
        authService.authenticate(token);//authenticate the token
        User user = authService.getUser(token);//get user details

        cartService.deleteCartItem(cartItemId, user);
        return new ResponseEntity<>("Deleted from cart", HttpStatus.OK);
    }
}
