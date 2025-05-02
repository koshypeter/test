package com.ecommerce_v2.service;

import com.ecommerce_v2.Dtos.cart.AddToCartDto;
import com.ecommerce_v2.Dtos.cart.CartDto;
import com.ecommerce_v2.Dtos.cart.CartItemDto;
import com.ecommerce_v2.exceptions.CustomException;
import com.ecommerce_v2.model.Cart;
import com.ecommerce_v2.model.Product;
import com.ecommerce_v2.model.User;
import com.ecommerce_v2.repositories.CartRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service

public class CartService {
    @Autowired
    ProductService productService;
    @Autowired
    CartRepo cartRepo;

    public void addtocart(AddToCartDto addToCartDto, User user) {
        Product product=productService.findById(addToCartDto.getProductId());//check if product id is valid

        Cart cart = new Cart();
        cart.setProduct(product);
        cart.setUser(user);
        cart.setQuantity(addToCartDto.getQuantity());
        cart.setCreatedDate(new Date());

        cartRepo.save(cart);//save to cart
    }

    public CartDto listCartItems(User user) {
        final List<Cart> cartList=cartRepo.findAllByUserOrderByCreatedDateDesc(user);
        List<CartItemDto> cartItemDtoList = new ArrayList<>();
        Double totalCost= 0.0;
        for(Cart cart : cartList){
            CartItemDto cartItemDto = new CartItemDto(cart);
            totalCost= cartItemDto.getQuantity() * cart.getProduct().getPrice();
            cartItemDtoList.add(cartItemDto);
        }

        CartDto cartDto = new CartDto();
        cartDto.setTotalCost(totalCost);
        cartDto.setCartItemDtos(cartItemDtoList);
        return cartDto;
    }

    public void deleteCartItem(Integer cartItemId, User user) {
        Optional<Cart> cartOptional= cartRepo.findById(cartItemId);
        if(cartOptional.isEmpty()){
            throw new CustomException("cart item id is invalid :"+ cartItemId);
        }
        Cart cart = cartOptional.get();

        if(cart.getUser()!= user){
            throw new CustomException("cart item id invalid for user");
        }

        cartRepo.delete(cart);
    }
}
