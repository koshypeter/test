package com.ecommerce_v2.Dtos.cart;

import java.util.*;

public class CartDto {
    private List<CartItemDto> cartItemDtos;
    private Double totalCost;

    public CartDto() {
    }

    public List<CartItemDto> getCartItemDtos() {
        return cartItemDtos;
    }

    public void setCartItemDtos(List<CartItemDto> cartItemDtos) {
        this.cartItemDtos = cartItemDtos;
    }

    public Double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Double totalCost) {
        this.totalCost = totalCost;
    }
}
