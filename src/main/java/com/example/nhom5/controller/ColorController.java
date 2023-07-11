package com.example.nhom5.controller;

import com.example.nhom5.domain.Color;
import com.example.nhom5.service.ColorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/colors")
public class ColorController   {
    @Autowired
    ColorService colorService;

    @PostMapping("/add")
    public String add_Color(@RequestBody Color color){
        color.setColorName(color.getColorName().toLowerCase());
        colorService.addColor(color);
        return "Color added";
    }
}
