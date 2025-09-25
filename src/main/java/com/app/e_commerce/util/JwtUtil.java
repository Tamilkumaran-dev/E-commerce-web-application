package com.app.e_commerce.util;

import com.app.e_commerce.exception.CustomException.CommonException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    private final static String secret = "This-Secret-must-have-larger-bits-loooooooooooooooooorrrrrrrrrrrrrgggggggggeeeeeee";
    private final static long Expiration =1000*3000;
    private Key key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));


    public String generateToken(String email){
            return Jwts.builder()
                    .setSubject(email)
                    .issuedAt(new Date(System.currentTimeMillis()))
                    .setExpiration(new Date(System.currentTimeMillis() + Expiration))
                    .signWith(key, SignatureAlgorithm.HS256).compact();
    }


    public Boolean isValid(String token){
        Claims claims = Jwts.parser().setSigningKey(key).build().parseSignedClaims(token).getBody();
        if(claims == null){
            throw new CommonException("Token is invalid");
        }
        else {
            return true;
        }
    }

    public Claims decodeToken(String token){
        return Jwts.parser().setSigningKey(key).build().parseSignedClaims(token).getBody();
    }
}
