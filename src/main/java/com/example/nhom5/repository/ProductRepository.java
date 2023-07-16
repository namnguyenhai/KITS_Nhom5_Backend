package com.example.nhom5.repository;

import com.example.nhom5.domain.Product;
import com.example.nhom5.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {
    public Product findById(int productId);

}
