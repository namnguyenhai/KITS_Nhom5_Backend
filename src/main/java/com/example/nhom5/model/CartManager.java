package com.example.nhom5.model;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.ArrayList;
import java.util.List;

@Component
@SessionAttributes
//@SessionScope
public class CartManager {
    private List<CartItem> cartItems = new ArrayList<>();


    public void addToCart(CartItem item) {
        cartItems.add(item);
    }
    public double totalPrice(){
        double result=0;
      for(CartItem cartItem : cartItems){
         result+= cartItem.getUnitPrice()*cartItem.getQuantity();
        }
        return result;
    }

    public void removeFromCart(CartItem item) {
        cartItems.remove(item);
    }
    public void removeAllCart(){
        cartItems.clear();

    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }
}
