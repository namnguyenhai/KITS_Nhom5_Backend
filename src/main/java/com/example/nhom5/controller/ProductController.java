package com.example.nhom5.controller;

import com.example.nhom5.domain.Product;
import com.example.nhom5.domain.ProductImage;
import com.example.nhom5.service.CategoryService;
import com.example.nhom5.service.ProductImageService;
import com.example.nhom5.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")

public class ProductController {
    @Autowired
    ProductService productService;

    @Autowired
    ProductImageService productImageService;
//    @Autowired
//    CategoryService categoryService;
//    @PostMapping("/addProductNewCategory")
//    public String add_Product_New_Category(@RequestBody Product product){
//        categoryService.addCategory(product.getCategory());
//        productService.addProduct(product);
//        return "Product added";
//    }

    @PostMapping("/add")
    public String add_Product(@RequestBody Product product){
        productService.addProduct(product);
        return "Product added";
    }



}
