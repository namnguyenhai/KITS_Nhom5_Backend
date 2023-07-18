package com.example.nhom5.controller;

import com.example.nhom5.domain.Product;
import com.example.nhom5.model.ProductDTO;
import com.example.nhom5.service.ProductImageService;
import com.example.nhom5.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/products")

public class ProductController {
    @Autowired
    ProductService productService;

    @Autowired
    ProductImageService productImageService;

    @PostMapping("/add")
    public ResponseEntity<?> add_Product(@RequestBody Product product) {
        productService.addProduct(product);
        Map<String, Object> output = new HashMap<>();
        output.put("status", HttpStatus.OK.value());
        output.put("product", productService.getAllProducts());
        return new ResponseEntity<>(output, HttpStatus.OK);
    }

    //    @GetMapping("/getAllProducts")
//    public ResponseEntity<?> get_All_Products(){
//        Map<String,Object> output = new HashMap<>();
//        output.put("status",HttpStatus.OK.value());
//
//        output.put("product",productService.getAllProducts());
//
//        return new ResponseEntity<>(output,HttpStatus.OK);
//    }

    //        List<Map<String,Object>> data = productService.getAllProducts();
//        data.stream().map(entry -> {
//            Map<String,Object> maps = new HashMap<>();
//            en
//        })
//        Map<String,Object> newData = data.get(0);
//        for(String keys: newData.keySet()){
//            if(keys.equals("urlImage")){
//                newData.put(keys,"[1,2]");
//                System.out.println(newData.get(keys));
//            }
//
//        }
    @GetMapping("/getAllProducts")
    public ResponseEntity<?> get_All_Products() {
        Map<String, Object> output = new HashMap<>();
        output.put("status", HttpStatus.OK.value());

        output.put("product", productService.getAllProducts());

        return new ResponseEntity<>(output, HttpStatus.OK);
    }

    @GetMapping("/gettest")
    public ResponseEntity<?> get_Tests() {
        Map<String, Object> output = new HashMap<>();
        output.put("status", HttpStatus.OK.value());

        output.put("product", productService.getTests());

        return new ResponseEntity<>(output, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete_Product_By_Id(@PathVariable int id) {
        productService.deleteProduct(id);
        Map<String, Object> output = new HashMap<>();
        output.put("status", HttpStatus.OK.value());
        output.put("product", productService.getAllProducts());
        return new ResponseEntity<>(output, HttpStatus.OK);
    }
}
