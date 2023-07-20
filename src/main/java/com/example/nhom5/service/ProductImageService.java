package com.example.nhom5.service;

import com.example.nhom5.domain.ProductImage;

import java.util.List;

public interface ProductImageService {
    public ProductImage addProductImage(ProductImage productImage);
    public List<ProductImage> addListProductImages(List<ProductImage> productImages,int id);
}