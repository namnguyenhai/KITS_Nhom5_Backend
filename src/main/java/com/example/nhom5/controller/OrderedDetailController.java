package com.example.nhom5.controller;
import com.example.nhom5.domain.OrderedDetail;
import com.example.nhom5.dto.OrderedDetailDto;
import com.example.nhom5.service.OrderedDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("ordered-details")
public class OrderedDetailController {
    @Autowired
    OrderedDetailService orderedDetailService;
    @GetMapping("list")
    @ResponseBody
    public List<OrderedDetailDto>getListOrderedDetail(){
        return orderedDetailService.getAllOrderedDetail();
    }
    @PostMapping("/add")
    @ResponseBody
    public ResponseEntity<OrderedDetail> addOderOrderedDetail(@RequestBody OrderedDetail orderedDetail){
        orderedDetailService.addOrderedDetaiḷ(orderedDetail);
        return  ResponseEntity.ok(orderedDetail);
    }
}
