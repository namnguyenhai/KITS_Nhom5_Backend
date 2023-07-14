package com.example.nhom5.dto;


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
    private String userName;
    private String passWorld;
    private String role;
}
