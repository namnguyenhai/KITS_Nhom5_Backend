package com.example.nhom5.controller.user;

import com.example.nhom5.domain.Stock;
import com.example.nhom5.dto.CartItem;
import com.example.nhom5.dto.CartManager;
import com.example.nhom5.service.ProductService;
import com.example.nhom5.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
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

    @PostMapping("/add-cart")
    @ResponseBody
    public ResponseEntity<Void> addToCart(@RequestBody CartItem requestCart) {

        List<CartItem> cartItems = cartManager.getCartItems();

        for (CartItem cartItem : cartItems) {
            if (requestCart.getProductId()==(cartItem.getProductId())
                    && requestCart.getColorName().equals(cartItem.getColorName())
                    && requestCart.getSizeName().equals(cartItem.getSizeName())) {
                // Nếu sản phẩm đã tồn tại trong giỏ hàng, chỉ cập nhật số lượng
                int newQuantity = cartItem.getQuantity() + requestCart.getQuantity();
                cartItem.setQuantity(newQuantity);
                return ResponseEntity.ok().build();
            }
        }
        // Nếu sản phẩm không tồn tại trong giỏ hàng, thêm sản phẩm mới
        CartItem cart = new CartItem();
        cart.setProductId(requestCart.getProductId());
        cart.setProductName(requestCart.getProductName());
        cart.setUrlImage(requestCart.getUrlImage());
        cart.setQuantity(requestCart.getQuantity());
        cart.setUnitPrice(requestCart.getUnitPrice());
        cart.setColorName(requestCart.getColorName());
        cart.setSizeName(requestCart.getSizeName());
        cartManager.addToCart(cart);

        return ResponseEntity.ok().build();
    }


    @DeleteMapping("/remove-cart/{productId}/{sizeName}/{colorName}")
    public ResponseEntity<Void> removeFromCart(@PathVariable("productId") String productId,
                                               @PathVariable("sizeName") String sizeName,
                                               @PathVariable("colorName") String colorName) {
        List<CartItem> cartItems = cartManager.getCartItems();

        // Tìm sản phẩm trong giỏ hàng dựa trên productId
        CartItem cartItemToRemove = null;
        for (CartItem cartItem : cartItems) {
            if (productId.equals(cartItem.getProductId()) && sizeName.equals(cartItem.getSizeName()) && colorName.equals(cartItem.getColorName())) {
                cartItemToRemove = cartItem;
                break;
            }
        }

        // Nếu tìm thấy sản phẩm, xóa khỏi giỏ hàng
        if (cartItemToRemove != null) {
            cartManager.removeFromCart(cartItemToRemove);
            return ResponseEntity.ok().build();
        } else {
            // Nếu không tìm thấy sản phẩm, trả về lỗi 404 Not Found
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/remove-all-cart")
    public ResponseEntity<Void>removeAllCart(){
        cartManager.removeAllCart();
        return ResponseEntity.ok().build();

    }
    @PutMapping("/update-cart")
    @ResponseBody
    public ResponseEntity<Void> updateCart(@RequestBody CartItem requestCart) {

        List<CartItem> cartItems = cartManager.getCartItems();

        for (CartItem cartItem : cartItems) {
            if (requestCart.getProductId() == cartItem.getProductId()
                    && requestCart.getColorName().equals(cartItem.getColorName())
                    && requestCart.getSizeName().equals(cartItem.getSizeName())) {
                // Update số lượng
                int newQuantity = requestCart.getQuantity();
                cartItem.setQuantity(newQuantity);
                Stock stock = stockService.findStock(requestCart.getProductId(), requestCart.getSizeName(), requestCart.getColorName());
                if (cartItem.getQuantity() > stock.getQuantityStock()) {
                    // Sản phẩm hết hàng, trả về mã lỗi 400 Bad Request
                    return ResponseEntity.badRequest().build();
                }
                return ResponseEntity.ok().build();
            }
        }
        return ResponseEntity.notFound().build();
    }



}