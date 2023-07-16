package com.example.nhom5.controller;

import com.example.nhom5.domain.Size;
import com.example.nhom5.model.SizeDTO;
import com.example.nhom5.service.SizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sizes")
public class SizeController {
    @Autowired
    SizeService sizeService;

    @PostMapping("/add")
    public String add_Size(@RequestBody Size size){
        size.setSizeName(size.getSizeName().toUpperCase());
        sizeService.addSize(size);
        return "Size added";
    }

    @GetMapping("/getallsize")
    public List<SizeDTO> get_All_Size(){
        return sizeService.getAllSize();
    }
}
