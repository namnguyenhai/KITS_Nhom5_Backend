package com.example.nhom5.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/health")
public class HealthCheck {
    @GetMapping("/checkheath")
    public ResponseEntity<?> get_Check_Heath(){
        Map<String,Object> output = new HashMap<>();
        output.put("status", HttpStatus.OK.value());
        output.put("message","api still alive. YAYYYYYYYY");
        return new ResponseEntity<>(output,HttpStatus.OK);
    }
}
