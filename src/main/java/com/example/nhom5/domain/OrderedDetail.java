package com.example.nhom5.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "orderedDetails")
public class OrderedDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderedDetailId;
    private int quantityOrder;
    private String colorName;
    private String sizeName;
    @ManyToOne
    @JoinColumn(name = "productId")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "orderId")
    private Ordered order;

}
