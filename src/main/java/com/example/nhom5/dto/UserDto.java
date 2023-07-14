package com.example.nhom5.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto implements Serializable {
    private int userId;
    private String firstName;
    private String lastName;
    private String phone;
    private String address;
    private String image;
    private String email;
    private String username;
    private String password;
    private String role;

}
