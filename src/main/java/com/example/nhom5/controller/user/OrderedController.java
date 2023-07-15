package com.example.nhom5.controller.user;

import com.example.nhom5.dto.OrderedDto;
import com.example.nhom5.service.OrderedDetailService;
import com.example.nhom5.service.OrderedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("api/user/orders")

public class OrderedController {
    @Autowired
    OrderedService orderedService;
    @GetMapping("/list")
    @ResponseBody
    public List<OrderedDto>getListOrderd(){
        return orderedService.getAllOrdered();


    }
}
