package com.example.nhom5.controller;

import com.example.nhom5.domain.Product;
import com.example.nhom5.domain.ProductImage;
import com.example.nhom5.service.ColorService;
import com.example.nhom5.service.ProductImageService;
import com.example.nhom5.service.ProductService;
import com.example.nhom5.service.SizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product_images")
@CrossOrigin(origins = "http://localhost:3000")
public class ProductImageController {
    @Autowired
    ProductImageService productImageService;

    @Autowired
    ProductService productService;


    @PostMapping("/add")
    public String add_ProductImage(@RequestBody ProductImage productImage){
        productImageService.addProductImage(productImage);
        return "Product Image added";
    }

    @PostMapping("/add_list_image")
    public String add_List_ProductImage(@RequestBody List<ProductImage> productImages,int id){

        productImageService.addListProductImages(productImages,id);
        return "added list image";
    }


//    @PostMapping("/add_image_with_new_product")
//    public String add_Image_New_Product(@RequestBody ProductImage productImage){
//        productService.addProduct(productImage.getProduct());
//        productImageService.addProductImage(productImage);
//        return "Product and Image added";
//    }

}
