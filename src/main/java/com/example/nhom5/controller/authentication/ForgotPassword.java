package com.example.nhom5.controller.authentication;

import com.example.nhom5.domain.User;
import com.example.nhom5.model.RegisterRequestDto;
import com.example.nhom5.model.RegisterResponseDto;
import com.example.nhom5.service.UserService;
import com.example.nhom5.utils.UtilsService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

@RestController
@RequestMapping("/api/reset-password")
@CrossOrigin("http://localhost:3000/")
public class ForgotPassword {

    @Autowired
    private UtilsService utilsService;

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    JavaMailSender javaMailSender;

    @PostMapping("/user")
    @ResponseBody
    public ResponseEntity<Void> forgotPassword(@RequestParam("email") String email) {
        User user=userService.findByEmail(email);
        if(user==null){
            return ResponseEntity.badRequest().build();
        }else {

            user.setPassword(bCryptPasswordEncoder.encode("12345"));
            //mail
            SimpleMailMessage message=new SimpleMailMessage();
            message.setTo(user.getEmail());
            System.out.println(user.getEmail());
            message.setSubject("Reset Password Successfully!");
            String mes = "";
            mes+="\n You have successfully reset your password!";
            mes+="\n - Username: "+user.getUsername();
            mes+="\n The default password is 12345";
            mes+="\n Please login again and change your password for security";
            message.setText(mes);
            this.javaMailSender.send(message);
       }

        return ResponseEntity.ok().build();
    }
}