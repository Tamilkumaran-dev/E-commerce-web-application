package com.app.e_commerce.controller;

import com.app.e_commerce.Dto.DoneResponce;
import com.app.e_commerce.Dto.JwtTokenDto;
import com.app.e_commerce.service.OrderService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/order")
public class OrderController {

    private OrderService orderService;

    @PutMapping("/orderPlaced/{userId}")
    public ResponseEntity<DoneResponce> orderPlaced(@PathVariable("userId") Long id){
        return new ResponseEntity<>(orderService.placeOrder(id), HttpStatus.CREATED);
    }

    // get Order List
    @GetMapping("/getOrderList")
    public ResponseEntity<JwtTokenDto> getOrderList(HttpServletRequest request){
        return new ResponseEntity<>(orderService.getUserOrder(request),HttpStatus.OK);
    }

    @PutMapping("/cancelProduct/{userId}/{orderId}/{productId}")
    public ResponseEntity<DoneResponce> cancelProduct(@PathVariable("userId")Long userId,@PathVariable("orderId")Long orderId,@PathVariable("productId")Long productId){
        return new ResponseEntity<>(orderService.removeProduct(userId,orderId,productId),HttpStatus.OK);
    }



}
