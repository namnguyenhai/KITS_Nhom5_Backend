package com.example.nhom5.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CartItem {
  private int productId;
  private String productName;
  private String urlImage;
  private int quantity;
  private double unitPrice;
  private String colorName;
  private String sizeName;

}