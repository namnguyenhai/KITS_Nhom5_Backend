package com.example.nhom5.controller.user;

import com.example.nhom5.converter.OrderedConverter;
import com.example.nhom5.domain.Ordered;
import com.example.nhom5.domain.User;
import com.example.nhom5.dto.CartManager;
import com.example.nhom5.dto.OrderedDto;
import com.example.nhom5.service.OrderedService;
import com.example.nhom5.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("api/user/orders")

public class OrderedController {
    @Autowired
    OrderedService orderedService;
    @Autowired
    CartManager cartManager;
    @Autowired
    UserService userService;
    @Autowired
    OrderedConverter orderedConverter;
    @GetMapping("/list")
    @ResponseBody
    public List<OrderedDto>getListOrderd(){
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

        User user=userService.findUserById(userId);
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
        OrderedDto orderedDto=orderedConverter.toDto(orderedService.save(order));
        return  orderedDto;
    }


}
