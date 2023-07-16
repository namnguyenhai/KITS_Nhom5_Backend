package com.example.nhom5.controller;

import com.example.nhom5.domain.Color;
import com.example.nhom5.model.ColorDTO;
import com.example.nhom5.service.ColorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/getallcolor")
    public List<ColorDTO> get_All_Color(){
        return colorService.getAllColors();
    }
}
