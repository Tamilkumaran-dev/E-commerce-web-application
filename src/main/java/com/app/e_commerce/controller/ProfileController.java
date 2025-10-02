package com.app.e_commerce.controller;

import com.app.e_commerce.Dto.DoneResponce;
import com.app.e_commerce.Dto.JwtTokenDto;
import com.app.e_commerce.entity.ECommerceUser;
import com.app.e_commerce.repository.EcommerceUserRepo;
import com.app.e_commerce.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/profile")
@Tag(name = "Profile controller",
        description = "This controller is used for user profile based operation")
public class ProfileController {

    private UserService userService;

    @Operation(
            summary = "get customer profile end point",
            description = "This is used to get the customer profile"
    )
    @ApiResponse(
            responseCode = "200",
            description = "get the Customer profile"
    )
    @GetMapping("")
    public ResponseEntity<JwtTokenDto> getUserProfile(HttpServletRequest request){
        return new ResponseEntity<>(userService.getUserProfile(request), HttpStatus.OK);
    }

    @Operation(
            summary = "Check logged in end point",
            description = "This end point is used to check is user is logged in"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Check is user is Logged in"
    )
    @PostMapping("/isLoggedIn")
    public ResponseEntity<DoneResponce> isLoggedIn(HttpServletRequest request){
        return new ResponseEntity<>(userService.isLoggedInService(request),HttpStatus.OK);
    }

    @Operation(
            summary = "Customer profile Update end point",
            description = "This end point is used to update user the profile"
    )
    @ApiResponse(
            responseCode = "201",
            description = "Update user profile"
    )
    @PutMapping("/UpdateProfile")
    public ResponseEntity<DoneResponce> updateprofile(@RequestBody ECommerceUser user){
        return new ResponseEntity<>(userService.updateUserProfile(user),HttpStatus.ACCEPTED);
    }


}
