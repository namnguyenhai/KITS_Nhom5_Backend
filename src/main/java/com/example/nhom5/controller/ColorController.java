package com.example.nhom5.controller;

import com.example.nhom5.domain.Color;
import com.example.nhom5.model.ColorDTO;
import com.example.nhom5.service.ColorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/colors")
public class ColorController   {
    @Autowired
    ColorService colorService;

    @PostMapping("/add")
    public ResponseEntity<?> add_Color(@RequestBody Color color){
        color.setColorName(color.getColorName().toLowerCase());
        colorService.addColor(color);
        Map<String,Object> output = new HashMap<>();
        output.put("status", HttpStatus.OK.value());
        output.put("color",colorService.getAllColors());
        return new ResponseEntity<>(output,HttpStatus.OK);
    }

    @GetMapping("/getallcolor")
    public ResponseEntity<?> get_All_Color(){
        Map<String,Object> output = new HashMap<>();
        output.put("status",HttpStatus.OK.value());
        output.put("color",colorService.getAllColors());
        return new ResponseEntity<>(output,HttpStatus.OK);
    }
}