package com.example.nhom5.service;

import com.example.nhom5.domain.Size;
import com.example.nhom5.model.SizeDTO;
import org.springframework.stereotype.Service;

import java.util.List;


public interface SizeService {
    public Size addSize(Size size);
    public List<SizeDTO> getAllSize();

}