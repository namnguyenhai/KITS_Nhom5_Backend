package com.example.nhom5.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "product_image")
public class ProductImage implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productImageId;

    private String urlImage;

    @ManyToOne
    @JoinColumn(name = "productId")
    private Product product;
//
//    @ManyToOne
//    @JoinColumn(name = "color")
//    private Color color;
//
//    @ManyToOne
//    @JoinColumn(name = "size")
//    private Size size;


}