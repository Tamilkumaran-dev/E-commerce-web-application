package com.app.e_commerce.service;

import com.app.e_commerce.Dto.JwtTokenDto;
import com.app.e_commerce.Dto.LoginDto;
import com.app.e_commerce.entity.ECommerceUser;
import com.app.e_commerce.exception.CustomException.CommonException;
import com.app.e_commerce.repository.EcommerceUserRepo;
import com.app.e_commerce.util.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements LoginService{

    private EcommerceUserRepo userRepo;
    private PasswordEncoder passwordEncoder;
    private JwtUtil jwtUtil;

    @Override
    public JwtTokenDto signUpService(ECommerceUser user) {

        Optional<ECommerceUser> duplicateUser = userRepo.findByEmail(user.getEmail());

        if (user.getEmail().equals("") || user.getName().equals("") || user.getPassword().equals("")) {
            throw new CommonException("Haven't filled the user detail");
        }

        else if(duplicateUser.isPresent()){
            throw new CommonException("Email is already exist");
        }
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        ECommerceUser userSave = userRepo.save(user);
        JwtTokenDto jwtTokenDto = new JwtTokenDto("token","Saved", null);

        return jwtTokenDto;
     }

    @Override
    public JwtTokenDto loginService(LoginDto login) {

        Optional<ECommerceUser> user = userRepo.findByEmail(login.getEmail());

        if(login.getEmail() == null || login.getPassword() == null){
             throw new CommonException("haven't fill user details");
        }
        else if(user.isEmpty()){
             throw new CommonException("no user found");
        }
        else{
            String password = user.get().getPassword();
            if(!passwordEncoder.matches(login.getPassword(),password)){
                throw new CommonException("password is incorrect");
            }
            else {
                String token = jwtUtil.generateToken(login.getEmail());
                return new JwtTokenDto(token,"Successfully logged in",null);
            }
        }

    }


}
