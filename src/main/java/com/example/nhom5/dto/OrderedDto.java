package com.example.nhom5.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderedDto implements Serializable {
    private int orderId;
    private String status;
    private double totalPrice;
    private Date orderDate;
}
