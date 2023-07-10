package com.example.nhom5.controller;

import com.example.nhom5.domain.Category;
import com.example.nhom5.domain.Product;
import com.example.nhom5.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    public CategoryService categoryService;


    @PostMapping("/add")
    public String add_Category(@RequestBody Category category){
        categoryService.addCategory(category);
        return "Category added";
    }
}
