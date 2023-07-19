package com.example.nhom5.controller.user;
import com.example.nhom5.domain.Ordered;
import com.example.nhom5.domain.OrderedDetail;
import com.example.nhom5.dto.CartItem;
import com.example.nhom5.dto.CartManager;
import com.example.nhom5.dto.OrderedDetailDto;
import com.example.nhom5.service.OrderedDetailService;
import jakarta.persistence.criteria.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("api/user/ordered-details")

public class OrderedDetailController {
    CartManager cartManager;
    @Autowired
    OrderedDetailService orderedDetailService;
    @GetMapping("list")
    @ResponseBody
    public List<OrderedDetailDto>getListOrderedDetail(){
        return orderedDetailService.getAllOrderedDetail();
    }
//    @PostMapping("/add")
//    @ResponseBody
//    public ResponseEntity<OrderedDetail> addOrderedDetail(@RequestBody OrderedDetail orderedDetail){
//        orderedDetailService.addOrderedDetaiḷ(orderedDetail);
//        return  ResponseEntity.ok(orderedDetail);
//
//    }

}
