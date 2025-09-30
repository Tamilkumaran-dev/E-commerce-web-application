package com.app.e_commerce.service;

import com.app.e_commerce.Dto.CartDto;
import com.app.e_commerce.Dto.DoneResponce;
import com.app.e_commerce.Dto.JwtTokenDto;
import com.app.e_commerce.entity.ECommerceUser;
import com.app.e_commerce.entity.Product;
import com.app.e_commerce.repository.EcommerceUserRepo;
import com.app.e_commerce.util.JwtUtil;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class UserService {

    private EcommerceUserRepo userRepo;
    private ProductService productService;
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


    //At to cart
    public DoneResponce addToCart(Long productId, HttpServletRequest request){

        String pureToken = "";

        for (Cookie cookie : request.getCookies()) {
            if ("jwt".equals(cookie.getName())) {
                pureToken = cookie.getValue();
            }
        }

        Product product = productService.getProduct(productId).getProduct().get();
        String userEmail = jwtUtil.decodeToken(pureToken).getSubject();
        ECommerceUser user = userRepo.findByEmail(userEmail).get();

        List<Product> cart = user.getCart();

        cart.add(product);

        user.setCart(cart);

        for(Product pro : cart){
            System.out.println(pro.getProductName());
        }

        userRepo.save(user);

        return new DoneResponce("Add successfully",true);

    }


    //get user cart
    public JwtTokenDto getUserCart(HttpServletRequest request){

        String pureToken = "";

        for (Cookie cookie : request.getCookies()) {
            if ("jwt".equals(cookie.getName())) {
                pureToken = cookie.getValue();
            }
        }
        String userEmail = jwtUtil.decodeToken(pureToken).getSubject();
        Optional<ECommerceUser> user = userRepo.findByEmail(userEmail);
        CartDto cart = new CartDto(user.get().getId(),user.get().getCart());

        return new JwtTokenDto("token","get the user car", Optional.of(cart));
    }


    // Remove product for cart
    public DoneResponce removeProduct(Long userId, Long productId){

        ECommerceUser user = userRepo.findById(userId).get();

        List<Product> cart = user.getCart();

        // find the first matching product
        Product productToRemove = cart.stream()
                .filter(p -> p.getId().equals(productId))
                .findFirst()
                .orElse(null);

        if (productToRemove != null) {
            cart.remove(productToRemove); // removes only one occurrence
            userRepo.save(user);
            return new DoneResponce("Product removed from cart", true);
        }
        else{
            return new DoneResponce("Product not found in cart", false);
        }


    }

}
