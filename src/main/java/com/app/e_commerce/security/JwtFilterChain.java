package com.app.e_commerce.security;

import com.app.e_commerce.entity.ECommerceUser;
import com.app.e_commerce.exception.CustomException.CommonException;
import com.app.e_commerce.repository.EcommerceUserRepo;
import com.app.e_commerce.util.JwtUtil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class JwtFilterChain extends OncePerRequestFilter {

    private JwtUtil jwtUtil;
    private EcommerceUserRepo userRepo;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String path = request.getRequestURI();

        String pureToken = "";

        // Skip JWT validation for auth endpoints
        if (path.startsWith("/auth/")) {
            filterChain.doFilter(request, response);
            return;
        }
        if (path.startsWith("/home/")) {
            filterChain.doFilter(request, response);
            return;
        }


//         Get token from cookies
        if(request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if ("jwt".equals(cookie.getName())) {
                    pureToken = cookie.getValue();
                    try {
                        if (jwtUtil.isValid(pureToken)) {
                            Claims decodeToken = jwtUtil.decodeToken(pureToken);

                            String email = decodeToken.getSubject();

                            Optional<ECommerceUser> fetchUser = userRepo.findByEmail(email);
                            if (fetchUser == null) {
                                throw new CommonException("No user found");
                            } else {
                                String role = fetchUser.get().getRole();
                                Set<String> roles = new HashSet<>(List.of(role));
                                Set<GrantedAuthority> grantedAuthorities = roles.stream().map((r) -> new SimpleGrantedAuthority(r)).collect(Collectors.toSet());


                                UsernamePasswordAuthenticationToken generatedtoken = new UsernamePasswordAuthenticationToken(fetchUser.get().getEmail(), fetchUser.get().getPassword(), grantedAuthorities);

                                SecurityContextHolder.getContext().setAuthentication(generatedtoken);
                            }

                        }
                    }catch (io.jsonwebtoken.ExpiredJwtException ex) {
                        // ⚠️ Token expired → clear cookie
                        clearJwtCookie(response);
                        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                        response.getWriter().write("Token expired, please login again");
                        return;
                    } catch (io.jsonwebtoken.JwtException ex) {
                        // Invalid token → clear cookie too
                        clearJwtCookie(response);
                        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                        response.getWriter().write("Invalid token");
                        return;
                    }
                }
            }
        }
        filterChain.doFilter(request,response);
    }

    private void clearJwtCookie(HttpServletResponse response) {
        Cookie expiredCookie = new Cookie("jwt", null);
        expiredCookie.setPath("/");
        expiredCookie.setHttpOnly(true);
        expiredCookie.setMaxAge(0); // delete immediately
        response.addCookie(expiredCookie);
    }
}
