package com.example.nhom5.service;

import com.example.nhom5.domain.Color;
import com.example.nhom5.model.ColorDTO;

import java.util.List;

public interface ColorService {
    public Color addColor(Color color);
    public List<ColorDTO> getAllColors();
}
