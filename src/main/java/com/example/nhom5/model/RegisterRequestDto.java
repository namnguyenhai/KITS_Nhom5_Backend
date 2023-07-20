package com.example.nhom5.model;


import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RegisterRequestDto {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String address;
    private String image;
    private String email;
    private String username;
    private String password;
    private String role;
}
