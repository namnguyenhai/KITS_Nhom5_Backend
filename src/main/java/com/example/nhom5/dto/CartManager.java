package com.example.nhom5.dto;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.ArrayList;
import java.util.List;

@Component
@SessionScope
public class CartManager {
    private List<CartItem> cartItems = new ArrayList<>();

    public void addToCart(CartItem item) {
        cartItems.add(item);
    }

    public void removeFromCart(CartItem item) {
        cartItems.remove(item);
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }
}
