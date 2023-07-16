package com.example.nhom5.controller;

import com.example.nhom5.domain.Category;
import com.example.nhom5.domain.Product;
import com.example.nhom5.model.CategoryDTO;
import com.example.nhom5.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    CategoryService categoryService;


    @PostMapping("/add")
    public String add_Category(@RequestBody Category category){
        category.setCategoryName(category.getCategoryName().toLowerCase());
        categoryService.addCategory(category);
        return "Category added";
    }

    @GetMapping("/getallcategory")
    public List<CategoryDTO> get_All_Category(){
        return categoryService.getAllCategorys();
    }
}
