package com.example.nhom5.dto;

import com.example.nhom5.domain.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CartItem {
  private String productId;
  private int quantity;
  private double unitPrice;
  private String colorName;
  private String sizeName;

}