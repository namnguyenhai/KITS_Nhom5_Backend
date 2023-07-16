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
  private String productName;
  private int quantity;
  private double unitPrice;
  private String colorName;
  private String sizeName;

  public CartItem(Product product, Stock stock, Color color, Size size, int quantity) {
    this.productName = product.getProductName();
    this.quantity = quantity;
    this.unitPrice = stock.getPriceStock();
    this.colorName = color.getColorName();
    this.sizeName = size.getSizeName();
  }
}