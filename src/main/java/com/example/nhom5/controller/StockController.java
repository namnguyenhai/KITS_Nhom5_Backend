package com.example.nhom5.controller;

import com.example.nhom5.domain.Product;
import com.example.nhom5.domain.ProductImage;
import com.example.nhom5.domain.Stock;
import com.example.nhom5.service.ProductImageService;
import com.example.nhom5.service.ProductService;
import com.example.nhom5.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/stocks")
public class StockController {
    @Autowired
    StockService stockService;

    @Autowired
    ProductService productService;

    @Autowired
    ProductImageService productImageService;

    @GetMapping("/getstock/{id}")
    public ResponseEntity<?> get_Stock_By_ID(@PathVariable int id){
        stockService.getStockByID(id);
        Map<String,Object> output = new HashMap<>();
        output.put("status",HttpStatus.OK.value());
        output.put("stock",stockService.getStockByID(id));
        return new ResponseEntity<>(output,HttpStatus.OK);
    }


    @PostMapping("/add")
    public ResponseEntity<?> add_Stock(@RequestBody Stock stock){
        stockService.addStock(stock);
        Map<String,Object> output = new HashMap<>();
        output.put("status",HttpStatus.OK.value());
        output.put("stock",stockService.getAllStocks());
        return new ResponseEntity<>(output,HttpStatus.OK);
    }

    @PostMapping("/add_stock_new_product")
    public ResponseEntity<?> add_Stock_New_Product(@RequestBody Stock stock){
        Product pr = productService.addProduct(stock.getProduct());
        productImageService.addListProductImages(stock.getProduct().getProductImages(),pr.getProductId());
        stockService.addStock(stock);
        Map<String, Object> output = new HashMap<>();
        output.put("status", HttpStatus.OK.value());
        output.put("product",productService.getAllProducts());
        return new ResponseEntity<>(output,HttpStatus.OK) ;
    }
    /*
    * {
    "quantityStock": 12,
    "priceStock": 10000,
    "product":{
        "productName": "nike air",
        "brand": "nike",
        "description": "giay choi bong ro",
        "category": {
            "categoryName": "quan"
        },
        "productImages": [
            {
                "urlImage" : "https://test1.png",
                "product": {

                }
            },
            {
                "urlImage" : "https://test2.png",
                "product": {

                }
            }
        ]
    },
    "color":{
        "colorName": "red"
    },
    "size":{
        "sizeName": "M"
    }
}
    * */
}
