package com.app.e_commerce.service;

import com.app.e_commerce.Dto.JwtTokenDto;
import com.app.e_commerce.Dto.LoginDto;
import com.app.e_commerce.entity.ECommerceUser;
import com.app.e_commerce.exception.CustomException.CommonException;
import com.app.e_commerce.repository.EcommerceUserRepo;
import com.app.e_commerce.util.JwtUtil;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
class AuthServiceImplTest {

    private static ECommerceUser user;
    private static LoginDto loginDto;

    // dependency class
    @Mock
    private EcommerceUserRepo userRepo;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private JwtUtil jwtUtil;


    @BeforeAll
    public static void methodBeforeAll(){
        user = new ECommerceUser();
        user.setName("Gohul");
        user.setEmail("Gohul@gmail.com");
        user.setPassword("Gohul");

        loginDto = new LoginDto();
        loginDto.setEmail("Gohul@gmail.com");
        loginDto.setPassword("Gohul");
    }

    //the class which create this test class
    @InjectMocks
    private AuthServiceImpl authService;

    @DisplayName("sign up new user")
    @Test
    public void signUpServiceTest() {
//        given the given is all given in the before all method


        given(userRepo.findByEmail(user.getEmail())).willReturn(Optional.empty());
        given(userRepo.save(user)).willReturn(user);

        JwtTokenDto returnJwt = authService.signUpService(user);
        assertThat(returnJwt.getMessage()).isEqualTo("Saved");
    }

    // if the user is already exist
    @DisplayName("sign up with existing email id throw error")
    @Test
    public void signUpIsAlreadyExistServiceTest() {
//        given
        ECommerceUser user = new ECommerceUser();
        user.setName("Gohul");
        user.setEmail("Gohul@gmail.com");
        user.setPassword("Gohul");

        //when
        given(userRepo.findByEmail(user.getEmail())).willReturn(Optional.of(user));

        //then
        assertThrows(CommonException.class,()->authService.signUpService(user));

        verify(userRepo,never()).save(any(ECommerceUser.class));
    }


    @DisplayName("Login user")
    @Test
    public void LoginUserWithoutError(){

        //given
        given(userRepo.findByEmail(loginDto.getEmail())).willReturn(Optional.of(user));
        given(passwordEncoder.matches(loginDto.getPassword(),user.getPassword())).willReturn(true);

        //then
        JwtTokenDto user1 = authService.loginService(loginDto);

        assertThat(user1.getStatus()).isEqualTo(true);
    }

    @DisplayName("Login user password is wrong")
    @Test
    public void LoginUserPasswordIsWrong(){

        //given
        given(userRepo.findByEmail(loginDto.getEmail())).willReturn(Optional.of(user));
        given(passwordEncoder.matches(loginDto.getPassword(),user.getPassword())).willReturn(false);

        //then
        assertThrows(CommonException.class,()->authService.loginService(loginDto));


    }
    @DisplayName("Login user not found")
    @Test
    public void LoginUserNotFound(){

        //given
        given(userRepo.findByEmail(loginDto.getEmail())).willReturn(Optional.empty());

        //then
        assertThrows(CommonException.class,()->authService.loginService(loginDto)).getMessage().equals("no user found");


    }

}