package com.example.nhom5.controller;

import com.example.nhom5.domain.Product;
import com.example.nhom5.domain.ProductImage;
import com.example.nhom5.domain.Size;
import com.example.nhom5.domain.Stock;
import com.example.nhom5.model.StockDTO;
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
import java.util.stream.Collectors;

@RestController
@RequestMapping("/stocks")
public class StockController {
    @Autowired
    StockService stockService;

    @Autowired
    ProductService productService;

    @Autowired
    ProductImageService productImageService;

    @GetMapping("/getallstocks")
    public ResponseEntity<?> get_All_Stock(){
        Map<String,Object> output = new HashMap<>();
        output.put("status",HttpStatus.OK.value());
        output.put("stock",stockService.getAllStocks());
        return new ResponseEntity<>(output,HttpStatus.OK);
    }
    @GetMapping("/getstock/{id}")
    public ResponseEntity<?> get_Stock_By_ID(@PathVariable int id){
        stockService.getStockByProductID(id);
        Map<String,Object> output = new HashMap<>();
        output.put("status",HttpStatus.OK.value());
        output.put("stock",stockService.getStockByProductID(id));
        return new ResponseEntity<>(output,HttpStatus.OK);
    }

    @GetMapping("/getstockcolorssizesbrands")
    public ResponseEntity<?> get_Stock_Color_Size_Brand(){
        Map<String,Object> output = new HashMap<>();
        output.put("status",HttpStatus.OK.value());
        output.put("stock",stockService.getStockColorsSizesBrands());
        return new ResponseEntity<>(output,HttpStatus.OK);
    }

    @GetMapping("/getstockbyproductcolorsize/{productID}/{colorID}/{sizeID}")
    public ResponseEntity<?> get_Stock_By_Product_Color_Size(@PathVariable int productID , @PathVariable String colorID, @PathVariable String sizeID){
        Map<String,Object> output = new HashMap<>();
        output.put("status",HttpStatus.OK.value());

        output.put("stock",stockService.getStockByProductColorSize(productID,colorID,sizeID));
        return new ResponseEntity<>(output,HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete_Stock_By_ID(@PathVariable int id){
        stockService.deleteStockByID(id);
        Map<String,Object> output = new HashMap<>();
        output.put("status",HttpStatus.OK.value());
        output.put("stock",stockService.getAllStocks());
        return new ResponseEntity<>(output,HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<?> add_Stock(@RequestBody Stock stock){
        Map<String,Object> output = new HashMap<>();
        output.put("test",stockService.existStocks(stock.getProduct().getProductId(),stock.getColor().getColorName(),stock.getSize().getSizeName()));
        Boolean checked = stockService.existStocks(stock.getProduct().getProductId(),stock.getColor().getColorName(),stock.getSize().getSizeName());
        if(checked == false){

            Stock stock1 = stockService.addStock(stock);

            output.put("status",HttpStatus.OK.value());
            output.put("stock",stockService.getStockByProductID(stock1.getProduct().getProductId()));
            return new ResponseEntity<>(output,HttpStatus.OK);
        }
        else {
            output.put("status", HttpStatus.BAD_REQUEST.value());
            output.put("message", "duplicate stock with color size product");
            return new ResponseEntity<>(output,HttpStatus.BAD_REQUEST) ;
        }
    }

    @PostMapping("/add_stock_new_product")
    public ResponseEntity<?> add_Stock_New_Product(@RequestBody Stock stock){
        Map<String, Object> output = new HashMap<>();
        try {
            Product pr = productService.addProduct(stock.getProduct());
            productImageService.addListProductImages(stock.getProduct().getProductImages(),pr.getProductId());
            stockService.addStock(stock);
            output.put("status", HttpStatus.OK.value());
            output.put("product",productService.getAllProducts());
            return new ResponseEntity<>(output,HttpStatus.OK) ;
        }
        catch (Exception e){
            output.put("status", HttpStatus.BAD_REQUEST.value());
            output.put("message", "duplicate id or name of product");
            return new ResponseEntity<>(output,HttpStatus.BAD_REQUEST) ;
        }
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
