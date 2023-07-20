package com.example.nhom5.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data

@NoArgsConstructor
public class SizeDTO {
    private String sizeName;

    public SizeDTO(String sizeName) {
        this.sizeName = sizeName;
    }
}