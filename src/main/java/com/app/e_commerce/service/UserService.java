package com.app.e_commerce.service;

import com.app.e_commerce.Dto.CartDto;
import com.app.e_commerce.Dto.DoneResponce;
import com.app.e_commerce.Dto.JwtTokenDto;
import com.app.e_commerce.ECommerceApplication;
import com.app.e_commerce.entity.ECommerceUser;
import com.app.e_commerce.entity.Product;
import com.app.e_commerce.entity.UserPersonalDetail;
import com.app.e_commerce.exception.CustomException.CommonException;
import com.app.e_commerce.repository.EcommerceUserRepo;
import com.app.e_commerce.util.JwtUtil;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
@Transactional
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

    public DoneResponce isLoggedInService(HttpServletRequest request){

        String pureToken = "";

        for (Cookie cookie : request.getCookies()) {
            if ("jwt".equals(cookie.getName())) {
                pureToken = cookie.getValue();
            }
        }

        String userEmail = jwtUtil.decodeToken(pureToken).getSubject();
        ECommerceUser user = userRepo.findByEmail(userEmail).get();

        return new DoneResponce(user.getRole(), true);
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






//    public DoneResponce updateUserProfile(ECommerceUser user){
//        System.out.println("id" + user.getId());
//        ECommerceUser fetchUser = userRepo.findById(user.getId()).orElseThrow(()->new CommonException("no user found"));
//        if(fetchUser.getEmail().equals(user.getEmail())){
//            fetchUser.setName(user.getName());
//            if (user.getPersonalDetail() != null) {
//                fetchUser.getPersonalDetail().setMobileNo(user.getPersonalDetail().getMobileNo());
//                fetchUser.getPersonalDetail().setAddress(user.getPersonalDetail().getAddress());
//            }
//            userRepo.save(fetchUser);
//            return new DoneResponce("Updated successfully",true);
//        }
//
//        else{
//            if(userRepo.findByEmail(user.getEmail()).isEmpty()){
//                fetchUser.setEmail(user.getEmail());
//                fetchUser.setName(user.getName());
//                if (user.getPersonalDetail() != null) {
//                    fetchUser.getPersonalDetail().setMobileNo(user.getPersonalDetail().getMobileNo());
//                    fetchUser.getPersonalDetail().setAddress(user.getPersonalDetail().getAddress());
//                }
//                userRepo.save(fetchUser);
//                return new DoneResponce("Updated successfully",true);
//            }
//            else {
//                throw new CommonException("the emailId is allready used");
//            }
//
//        }
//    }



    public DoneResponce updateUserProfile(ECommerceUser user) {
        System.out.println("id: " + user.getId());
        System.out.println("user " + user.getName()+user.getEmail()+user.getPersonalDetail().getMobileNo()+user.getPersonalDetail().getAddress());

        ECommerceUser fetchUser = userRepo.findById(user.getId())
                .orElseThrow(() -> new CommonException("no user found"));

        // update name & email
        if (fetchUser.getEmail().equals(user.getEmail())
                || userRepo.findByEmail(user.getEmail()).isEmpty()) {

            fetchUser.setName(user.getName());
            fetchUser.setEmail(user.getEmail());

            // handle personal details safely
            if (user.getPersonalDetail() != null) {
                if (fetchUser.getPersonalDetail() == null) {
                    UserPersonalDetail detail = new UserPersonalDetail();
                    detail.setUser(fetchUser); // 🔑 important for mappedBy
                    fetchUser.setPersonalDetail(detail);
                }
                fetchUser.getPersonalDetail().setMobileNo(user.getPersonalDetail().getMobileNo());
                fetchUser.getPersonalDetail().setAddress(user.getPersonalDetail().getAddress());
            }

            userRepo.save(fetchUser);
            return new DoneResponce("Updated successfully", true);
        } else {
            throw new CommonException("The emailId is already used");
        }
    }

}
