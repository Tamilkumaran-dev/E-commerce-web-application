package com.app.e_commerce.controller;

import com.app.e_commerce.Dto.DoneResponce;
import com.app.e_commerce.Dto.JwtTokenDto;
import com.app.e_commerce.Dto.OrdersDto;
import com.app.e_commerce.entity.Order;
import com.app.e_commerce.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/order")
@Tag(name = "Order controller",
        description = "This controller is used for Order operation.")
public class OrderController {

    private OrderService orderService;

    @Operation(
            summary = "order placed end point",
            description = "In this we will transfer the cart data to order data"
    )
    @ApiResponse(
            responseCode = "201",
            description = "Order is created"
    )
    @PutMapping("/orderPlaced/{userId}")
    public ResponseEntity<DoneResponce> orderPlaced(@PathVariable("userId") Long id){
        return new ResponseEntity<>(orderService.placeOrder(id), HttpStatus.CREATED);
    }

    // get Order List
    @Operation(
            summary = "Get order list end point",
            description = "get order list of the customer"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Get order list"
    )
    @GetMapping("/getOrderList")
    public ResponseEntity<JwtTokenDto> getOrderList(HttpServletRequest request){
        return new ResponseEntity<>(orderService.getUserOrder(request),HttpStatus.OK);
    }

    @Operation(
            summary = "Cancel order end point",
            description = "Cancel order, will remove the order form the order table"
    )
    @ApiResponse(
            responseCode = "200",
            description = "cancel order"
    )
    @PutMapping("/cancelProduct/{userId}/{orderId}/{productId}")
    public ResponseEntity<DoneResponce> cancelProduct(@PathVariable("userId")Long userId,@PathVariable("orderId")Long orderId,@PathVariable("productId")Long productId){
        return new ResponseEntity<>(orderService.removeProduct(userId,orderId,productId),HttpStatus.OK);
    }

    @Operation(
            summary = "Admin access the order list end point",
            description = "admin access for the orders list,"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Get all order for deliver the order"
    )
    @GetMapping("/allOrders/{page}/{size}")
    public ResponseEntity<List<OrdersDto>> getAllProduct(@PathVariable("page") int page, @PathVariable("size") int size){
        return new ResponseEntity<>(orderService.listOfOrders(page,size),HttpStatus.OK);
    }

    @Operation(
            summary = "Update the order status end point",
            description = "This end point is used by admin to update the status"
    )
    @ApiResponse(
            responseCode = "201",
            description = "update the order status"
    )
    @PutMapping("/updateOrder/{orderId}/{orderStatus}")
    public ResponseEntity<DoneResponce> updateStatus(@PathVariable("orderId") Long orderId,@PathVariable("orderStatus") String status){
        return new ResponseEntity<>(orderService.updateOrderStatus(orderId,status),HttpStatus.ACCEPTED);
    }

}
