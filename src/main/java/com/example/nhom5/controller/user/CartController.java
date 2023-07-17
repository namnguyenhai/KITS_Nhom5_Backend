package com.example.nhom5.controller.user;

import com.example.nhom5.dto.CartItem;
import com.example.nhom5.dto.CartManager;
import com.example.nhom5.service.ProductService;
import com.example.nhom5.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CartController {
    private final CartManager cartManager;
    @Autowired
    private ProductService productService;
    @Autowired
    private StockService stockService;

    public CartController(CartManager cartManager) {
        this.cartManager = cartManager;
    }

    @GetMapping("/cart")
    public List<CartItem> getCartItems() {
        return cartManager.getCartItems();
    }

//    @PostMapping("/add-cart/{id}")
//    public void addToCart(@RequestBody CartItemRequest request,@PathVariable("id") int productId) {
//        Product product =productService.findProductById(productId);
//        CartItem cartItem = new CartItem();
//        cartItem.setProductName(product.getProductName());
//        Stock stock=stockService.findStockByIdProduct(productId);
//        cartItem.setQuantity(request.getQuantity());
//        cartItem.setUnitPrice(stock.getPriceStock());
//        //cartItem.setColorName(stock.getColor());
//        //cartItem.setSizeName(stock.getSize());
//        cartManager.addToCart(cartItem);

    @PostMapping("/add-cart")
    public void addToCart(@RequestBody CartItem requetCart) {
        CartItem cart =new CartItem();
        cart.setProductId(requetCart.getProductId());
        cart.setUnitPrice(requetCart.getUnitPrice());
        cart.setColorName(requetCart.getColorName());
        cart.setSizeName(requetCart.getSizeName());
        cartManager.addToCart(cart);

    }
}