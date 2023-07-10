package com.example.nhom5.controller;

import com.example.nhom5.domain.ProductImage;
import com.example.nhom5.service.ColorService;
import com.example.nhom5.service.ProductImageService;
import com.example.nhom5.service.ProductService;
import com.example.nhom5.service.SizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product_images")
public class ProductImageController {
    @Autowired
    ProductImageService productImageService;

    @Autowired
    ProductService productService;

    @Autowired
    ColorService colorService;

    @Autowired
    SizeService sizeService;
    @RequestMapping("/addProductImage")
    public String add_ProductImage(@RequestBody ProductImage productImage){
        productImageService.addProductImage(productImage);
        return "Product Image added";
    }

    @RequestMapping("/addProductImage_NewProduct")
    public String add_ProductImage_New(@RequestBody ProductImage productImage){
        productService.addProduct(productImage.getProduct());
        colorService.addColor(productImage.getColor());
        sizeService.addSize(productImage.getSize());
        productImageService.addProductImage(productImage);
        return "Product Image added";
    }
}
