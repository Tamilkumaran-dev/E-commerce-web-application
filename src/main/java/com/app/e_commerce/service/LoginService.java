package com.app.e_commerce.service;

import com.app.e_commerce.Dto.JwtTokenDto;
import com.app.e_commerce.Dto.LoginDto;
import com.app.e_commerce.entity.ECommerceUser;

public interface LoginService {

    JwtTokenDto signUpService(ECommerceUser user);

    JwtTokenDto loginService(LoginDto login);

}
