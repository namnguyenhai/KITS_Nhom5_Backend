package com.example.nhom5.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "category")

public class Category implements Serializable {
    @Id
    private String categoryName;

    @OneToMany(mappedBy = "category")
    private List<Product> products;
}