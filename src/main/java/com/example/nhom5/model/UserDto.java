package com.example.nhom5.model;

import lombok.*;

import java.io.Serializable;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
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
