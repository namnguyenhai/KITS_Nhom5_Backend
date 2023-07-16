package com.example.nhom5.service.impl;

import com.example.nhom5.domain.Color;
import com.example.nhom5.model.ColorDTO;
import com.example.nhom5.repository.ColorRepository;
import com.example.nhom5.service.ColorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ColorServiceImpl implements ColorService {
    @Autowired
    private ColorRepository colorRepository;
    @Override
    public Color addColor(Color color) {
        return colorRepository.saveAndFlush(color);
    }

    @Override
    public List<ColorDTO> getAllColors() {
        return colorRepository.getAllColor();
    }
}
