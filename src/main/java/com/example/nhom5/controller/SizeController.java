package com.example.nhom5.controller;

import com.example.nhom5.domain.Size;
import com.example.nhom5.model.SizeDTO;
import com.example.nhom5.service.SizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/sizes")
public class SizeController {
    @Autowired
    SizeService sizeService;

    //    @PostMapping("/add")
//    public String add_Size(@RequestBody Size size){
//        size.setSizeName(size.getSizeName().toUpperCase());
//        sizeService.addSize(size);
//        return "Size added";
//    }
    @PostMapping("/add")
    public ResponseEntity<?> add_Size(@RequestBody Size size) {
        size.setSizeName(size.getSizeName().toUpperCase());
        sizeService.addSize(size);
        Map<String, Object> out = new HashMap<>();
        out.put("status", HttpStatus.OK.value());
        out.put("size", sizeService.getAllSize());
        return new ResponseEntity<>(out, HttpStatus.OK);
    }

    @GetMapping("/getallsize")
    public ResponseEntity<?> get_All_Size() {
        Map<String, Object> output = new HashMap<>();
        output.put("status", HttpStatus.OK.value());
        output.put("size", sizeService.getAllSize());
        return new ResponseEntity<>(output, HttpStatus.OK);
    }

//    @DeleteMapping("/delete_by_sizename/{sizeName}")
//    public ResponseEntity<?> delete_By_Sizename(@PathVariable String sizeName){
//        sizeService.deleteSize(sizeName);
//        Map<String,Object> output = new HashMap<>();
//        output.put("status",HttpStatus.OK.value());
//        output.put("size", sizeService.getAllSize());
//        return new ResponseEntity<>(output,HttpStatus.OK);
//    }
}
