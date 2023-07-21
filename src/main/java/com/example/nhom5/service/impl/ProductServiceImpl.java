package com.example.nhom5.service.impl;

import com.example.nhom5.domain.Product;
import com.example.nhom5.model.ProductDTO;
import com.example.nhom5.model.TestDTO;
import com.example.nhom5.repository.ProductRepository;
import com.example.nhom5.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Override
    public Product addProduct(Product product) {
        return productRepository.saveAndFlush(product);
    }

    @Override
    public List<Map<String, Object>> getAllProductsAndStocks() {
        return productRepository.getAllProductAndStock();
    }

    @Override
    public List<Map<String,Object>> getAllProducts() {
        return productRepository.getAllProductsInfo();
    }

//    @Override
//    public List<ProductDTO> getAllProducts() {
//        return productRepository.getAllProduct();
//    }

    @Override
    public List<Map<String, Object>> getTests() {

        return productRepository.getTest();
    }


    @Override
    public String deleteProduct(int id) {
        productRepository.deleteById(id);
        return "Product deleted";
    }

    @Override
    public List<Map<String, Object>> findProductByID(int productID) {
        return productRepository.findProductById(productID);
    }

    @Override
    public List<Map<String, Object>> findProductByName(String name) {
        return productRepository.findProductByName(name);
    }

    @Override
    public List<Map<String, Object>> getAllProductBrand() {
        return productRepository.getProductBrand();
    }

    @Override
    public List<Map<String, Object>> filterProductAttribute(String brand, String size, String color, Double minPrice, Double maxPrice) {
        return productRepository.filterProduct(brand,size,color,minPrice,maxPrice);
    }


}
