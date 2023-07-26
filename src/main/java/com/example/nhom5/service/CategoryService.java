package com.example.nhom5.service;

import com.example.nhom5.domain.Category;
import com.example.nhom5.domain.Product;
import com.example.nhom5.model.CategoryDTO;

import java.util.List;

public interface CategoryService {
    public Category addCategory(Category category);
    public List<CategoryDTO> getAllCategorys();
}