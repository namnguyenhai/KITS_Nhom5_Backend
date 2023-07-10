package com.example.nhom5.controller;

import com.example.nhom5.domain.ProductImage;
import com.example.nhom5.service.ProductImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product_images")
public class ProductImageController {
    @Autowired
    public ProductImageService productImageService;

    @RequestMapping("/addProductImage")
    public String add_ProductImage(@RequestBody ProductImage productImage){
        productImageService.addProductImage(productImage);
        return "Product Image added";
    }
}
