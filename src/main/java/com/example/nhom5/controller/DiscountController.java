package com.example.nhom5.controller;

import com.example.nhom5.domain.Discount;
import com.example.nhom5.service.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/discounts")
public class DiscountController {
    @Autowired
    DiscountService discountService;

    @PostMapping("/add")
    public String add_Discount(@RequestBody  Discount discount){

        discountService.addDiscount(discount);
        return "Discount added";
    }
}