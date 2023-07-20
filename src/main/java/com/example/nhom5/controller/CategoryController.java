package com.example.nhom5.controller;

import com.example.nhom5.domain.Category;
import com.example.nhom5.domain.Product;
import com.example.nhom5.model.CategoryDTO;
import com.example.nhom5.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/category")

public class CategoryController {
    @Autowired
    CategoryService categoryService;


    @PostMapping("/add")
    public ResponseEntity<?> add_Category(@RequestBody Category category) {
        category.setCategoryName(category.getCategoryName().toLowerCase());
        categoryService.addCategory(category);
        Map<String, Object> output = new HashMap<>();
        output.put("status", HttpStatus.OK.value());
        output.put("category", categoryService.getAllCategorys());
        return new ResponseEntity<>(output, HttpStatus.OK);
    }

    //    @GetMapping("/getallcategory")
//    public List<CategoryDTO> get_All_Category(){
//        return categoryService.getAllCategorys();
//    }
    @GetMapping("/getallcategory")
    public ResponseEntity<?> get_All_Category() {
        Map<String,Object> output = new HashMap<>();
        output.put("status",HttpStatus.OK.value());
        output.put("category",categoryService.getAllCategorys());
        return new ResponseEntity<>(output,HttpStatus.OK);
    }
}