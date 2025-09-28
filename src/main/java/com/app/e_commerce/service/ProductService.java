package com.app.e_commerce.service;

import com.app.e_commerce.Dto.DoneResponce;
import com.app.e_commerce.Dto.ProductResponce;
import com.app.e_commerce.entity.ECommerceUser;
import com.app.e_commerce.entity.Product;
import com.app.e_commerce.exception.CustomException.CommonException;
import com.app.e_commerce.repository.EcommerceUserRepo;
import com.app.e_commerce.repository.ProductRepo;
import com.app.e_commerce.util.JwtUtil;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductService {

        private ProductRepo productRepo;
        private EcommerceUserRepo userRepo;
        private JwtUtil jwtUtil;

        public DoneResponce addProduct(Product product){
            if(product.getProductName() == null || product.getDescription() == null|| product.getImage() == null || product.getPrice() <= 0 || product.getQuantity() <= 0 ){
                throw new CommonException("some fields are not filled");
            }
            else{
                productRepo.save(product);
                DoneResponce res = new DoneResponce("Successfully product is add",true);
                return res;
            }
        }

    public Page<Product> getAllProduct(int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size, Sort.by("updatedTime").descending());
        return productRepo.findAll(pageable);
    }

    public ProductResponce getProduct(Long productID){
       Optional<Product> product =  productRepo.findById(productID);
       if(product.isEmpty()){
           throw new CommonException("There is product in this id");
       }
       else {
           return new ProductResponce("product found", true, product);
       }
    }

    //At to cart
    public DoneResponce addToCart(Long productId, HttpServletRequest request){

            String pureToken = "";

            for (Cookie cookie : request.getCookies()) {
                if ("jwt".equals(cookie.getName())) {
                    pureToken = cookie.getValue();
                }
            }

            Product product = getProduct(productId).getProduct().get();
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


}
