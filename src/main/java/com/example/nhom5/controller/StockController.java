package com.example.nhom5.controller;

import com.example.nhom5.domain.Product;
import com.example.nhom5.domain.ProductImage;
import com.example.nhom5.domain.Stock;
import com.example.nhom5.service.ProductImageService;
import com.example.nhom5.service.ProductService;
import com.example.nhom5.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/stocks")
public class StockController {
    @Autowired
    StockService stockService;

    @Autowired
    ProductService productService;

    @Autowired
    ProductImageService productImageService;
    @PostMapping("/add")
    public String add_Stock(@RequestBody Stock stock){
        stockService.addStock(stock);
        return "Stock added";
    }

    @PostMapping("/addStockWithNewProduct")
    public String add_Stock_New_Product(@RequestBody Stock stock){
        Product pr = productService.addProduct(stock.getProduct());

        productImageService.addListProductImages(stock.getProduct().getProductImages(),pr.getProductId());
        stockService.addStock(stock);
        return "Stock added" ;
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
