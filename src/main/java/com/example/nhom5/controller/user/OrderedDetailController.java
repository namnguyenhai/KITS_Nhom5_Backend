package com.example.nhom5.controller.user;
import com.example.nhom5.model.CartManager;
import com.example.nhom5.model.OrderedDetailDto;
import com.example.nhom5.service.OrderedDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
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
