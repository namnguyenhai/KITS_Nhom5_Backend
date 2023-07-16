package com.example.nhom5.controller;

import com.example.nhom5.domain.Product;
import com.example.nhom5.model.ProductDTO;
import com.example.nhom5.service.ProductImageService;
import com.example.nhom5.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")

public class ProductController {
    @Autowired
    ProductService productService;

    @Autowired
    ProductImageService productImageService;

    @PostMapping("/add")
    public String add_Product(@RequestBody Product product){
        productService.addProduct(product);
        return "Product added";
    }

    @GetMapping("/getAllProducts")
    public List<ProductDTO> get_All_Products(){
        return productService.getAllProducts();
    }


}
