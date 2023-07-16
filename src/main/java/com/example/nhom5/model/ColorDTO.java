package com.example.nhom5.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ColorDTO {
    private String colorName;

    public ColorDTO(String colorName) {
        this.colorName = colorName;
    }
}
