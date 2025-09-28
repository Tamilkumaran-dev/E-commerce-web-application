package com.app.e_commerce.service;

import com.app.e_commerce.Dto.DoneResponce;
import com.app.e_commerce.Dto.JwtTokenDto;
import com.app.e_commerce.entity.ECommerceUser;
import com.app.e_commerce.repository.EcommerceUserRepo;
import com.app.e_commerce.util.JwtUtil;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class UserService {

    private EcommerceUserRepo userRepo;
    private JwtUtil jwtUtil;

    public JwtTokenDto getUserProfile(HttpServletRequest request){

        String pureToken = "";

        for (Cookie cookie : request.getCookies()) {
            if ("jwt".equals(cookie.getName())) {
                pureToken = cookie.getValue();
            }
        }
        String userEmail = jwtUtil.decodeToken(pureToken).getSubject();
        Optional<?> user = userRepo.findByEmail(userEmail);

        return new JwtTokenDto("token","profile sent",user);

    }

    public DoneResponce isLoggedInService(){
        return new DoneResponce("user is logged in",true);
    }

}
