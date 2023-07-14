package com.example.nhom5.controller;

import com.example.nhom5.domain.Product;
import com.example.nhom5.domain.ProductImage;
import com.example.nhom5.model.ProductDTO;
import com.example.nhom5.model.ProductMultiTableDTO;
import com.example.nhom5.service.CategoryService;
import com.example.nhom5.service.ProductImageService;
import com.example.nhom5.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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


    @GetMapping("/getallProduct")
    public List<ProductDTO> find_All_Product(){
        return productService.getAllProduct();
    }

    @GetMapping("/getMultiple")
    public List<ProductMultiTableDTO> find_Multi_Table(){
        return productService.getMultipleTableProduct();
    }
    @GetMapping("/test")
    public String test(){
        return "OK ne";
    }
}
