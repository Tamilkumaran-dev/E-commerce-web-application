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

    public Page<Product> searchProduct(String product, int page, int size){

            if(product.equals("AllProduct")){
                return getAllProduct(page,size);
            }
            else {
                Pageable pageable = PageRequest.of(page - 1 ,size);
                return productRepo.searchProducts(product, pageable);
            }
    }
}
