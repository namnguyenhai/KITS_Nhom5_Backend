package com.example.nhom5.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderedDetailDto implements Serializable {
    private int orderedDetailId;
    private int quantityOrder;
    private double unitPrice;
    private String colorName;
    private String sizeName;
}
