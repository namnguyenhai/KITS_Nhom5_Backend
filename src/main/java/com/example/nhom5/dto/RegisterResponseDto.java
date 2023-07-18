package com.example.nhom5.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterResponseDto implements Serializable {
    private String message;
    private Object data;
    private String error;
    private String messageCode;
    private String token;
}