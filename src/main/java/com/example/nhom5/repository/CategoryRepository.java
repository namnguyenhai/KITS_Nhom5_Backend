package com.example.nhom5.repository;

import com.example.nhom5.domain.Category;
import com.example.nhom5.model.CategoryDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Integer> {
    @Query(value = "select new com.example.nhom5.model.CategoryDTO(cate.categoryName) from Category cate")
    List<CategoryDTO> getAllCategory();
}