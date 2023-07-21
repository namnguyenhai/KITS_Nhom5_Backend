package com.example.nhom5.controller.user;

import com.example.nhom5.converter.OrderedConverter;
import com.example.nhom5.domain.Ordered;
import com.example.nhom5.domain.OrderedDetail;
import com.example.nhom5.domain.Product;
import com.example.nhom5.domain.User;
import com.example.nhom5.model.CartItem;
import com.example.nhom5.model.CartManager;
import com.example.nhom5.model.OrderedDto;
import com.example.nhom5.service.*;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("api/user/orders")
@CrossOrigin("http://localhost:3000")

public class OrderedController {
    @Autowired
    OrderedService orderedService;
    @Autowired
    OrderedDetailService orderedDetailService;
    @Autowired
    StockService stockService;
    @Autowired
    CartManager cartManager;
    @Autowired
    UserService userService;
    @Autowired
    ProductService productService;
    @Autowired
    OrderedConverter orderedConverter;
    @Autowired
    JavaMailSender javaMailSender;

    @GetMapping("/list")
    @ResponseBody
    public List<OrderedDto> getListOrderd() {
        return orderedService.getAllOrdered();


    }

    @PostMapping("/checkout")
    @ResponseBody
    public OrderedDto checkout(HttpServletRequest request) {
        // Lấy thông tin từ cookie
        Cookie[] cookies = request.getCookies();
        int userId = 0; // Giá trị mặc định

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("token".equals(cookie.getName())) {
                    // Lấy giá trị token từ cookie
                    String token = cookie.getValue();

                    // Sử dụng token để lấy userId từ UserService
                    User user = userService.findByToken(token);
                    if (user != null) {
                        userId = user.getUserId();

                    }

                    break;
                }
            }
        }

        User user = userService.findUserById(userId);
        String status = "Success";
        double totalPrice = cartManager.totalPrice();
        // Tạo đối tượng Order
        Ordered order = new Ordered();
        order.setUser(user);
        order.setStatus(status);
        order.setTotalPrice(totalPrice);
        //lấy ngày giờ hiện tại
        Date orderDate = new Date();
        order.setOrderDate(orderDate);
        OrderedDto orderedDto = orderedConverter.toDto(orderedService.save(order));
        //Tạo đối tượng orderedDetails
        List<CartItem> cartItems = cartManager.getCartItems();
        List<OrderedDetail> orderedDetails = new ArrayList<>();

        for (CartItem cartItem : cartItems) {
            OrderedDetail orderedDetail = new OrderedDetail();
            orderedDetail.setQuantityOrder(cartItem.getQuantity());
            orderedDetail.setColorName(cartItem.getColorName());
            orderedDetail.setSizeName(cartItem.getSizeName());
            orderedDetail.setUnitPrice(cartItem.getUnitPrice());
            int productId = cartItem.getProductId();
            Product product = productService.findProductById(productId);
            orderedDetail.setProduct(product);
            orderedDetail.setOrder(order);
            orderedDetails.add(orderedDetail);
        }

        orderedDetailService.addOrderedDetails(orderedDetails);
        //update quantity in stock



        //gui mail đặt hàng thành công cho khách hàng
        SimpleMailMessage message=new SimpleMailMessage();
        message.setTo(user.getEmail());
        System.out.println(user.getEmail());
        message.setSubject("Order Successfully!");
        String mes = "";
        mes += "\n Dear Mr/Mrs " + user.getFirstName()+user.getLastName()+ ",";
        mes += "\n Thank you for visiting us and making your purchase";

        mes += "\n Your order includes: ";
        int index=1;
        for (CartItem cartItem:cartItems) {

            mes += "\n" + index + ". " + cartItem.getProductName() + ", quantity: " + cartItem.getQuantity();
            index++;
        }
        mes += "\n Total order price: " + order.getTotalPrice()+"$";
        mes += "\n Your order for another quarter has been processed and will ship in the next few days";
        message.setText(mes);
        this.javaMailSender.send(message);
        stockService.updateStockQuantity(cartItems);
        cartManager.removeAllCart();


        return orderedDto;
    }


}
