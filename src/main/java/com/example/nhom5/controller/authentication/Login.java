package com.example.nhom5.controller.authentication;

import com.example.nhom5.domain.User;
import com.example.nhom5.dto.RegisterRequestDto;
import com.example.nhom5.dto.RegisterResponseDto;
import com.example.nhom5.service.UserService;
import com.example.nhom5.utils.UtilsService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/login")
@CrossOrigin("http://localhost:3000/")
public class Login {

    @Autowired
    private UtilsService utilsService;

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping
    @ResponseBody
    public ResponseEntity<RegisterResponseDto> login(@RequestBody RegisterRequestDto registerRequest,
                                                     HttpServletResponse response) {
        // user entity
        User user = userService.findByUsername(registerRequest.getUsername());

        System.out.println("USER: " + user);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new RegisterResponseDto("User not found", "",
                    "", "USER_NOT_FOUND","",user.getUserId()));
        } else {
            // if user exists
            if (bCryptPasswordEncoder.matches(registerRequest.getPassword(), user.getPassword())) {
                //1. generate new token
                String newToken = utilsService.getRandomHexString(150);
                System.out.println("NEW TOKEN: " + newToken);
                //2. update token
                userService.updateTokenById(newToken, user.getUserId());
                // Set cookie
                Cookie cookie = new Cookie("token", newToken);
                cookie.setMaxAge(3600);
                response.addCookie(cookie);

                return ResponseEntity.status(HttpStatus.OK).body(new RegisterResponseDto("Login Successfully", "",
                        "", "", user.getToken(),user.getUserId()));
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new RegisterResponseDto("Wrong user or password", "", "", "USER_OR_PASSWORD_INVALID","",user.getUserId()));
            }
        }
    }
}