package com.example.nhom5.controller.user;

import com.example.nhom5.converter.OrderedConverter;
import com.example.nhom5.domain.Ordered;
import com.example.nhom5.domain.OrderedDetail;
import com.example.nhom5.domain.User;
import com.example.nhom5.dto.CartItem;
import com.example.nhom5.dto.CartManager;
import com.example.nhom5.dto.OrderedDto;
import com.example.nhom5.service.OrderedDetailService;
import com.example.nhom5.service.OrderedService;
import com.example.nhom5.service.UserService;
import jakarta.persistence.criteria.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.lang.annotation.Repeatable;
import java.util.Date;
import java.util.List;
import java.util.Set;

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
    @PostMapping("/checkout/{id}")
    @ResponseBody
    public OrderedDto checkout(@PathVariable("id") int userId) {
        // Lấy thông tin từ OrderRequest
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
