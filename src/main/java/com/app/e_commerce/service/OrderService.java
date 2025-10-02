package com.app.e_commerce.service;

import com.app.e_commerce.Dto.DoneResponce;
import com.app.e_commerce.Dto.JwtTokenDto;
import com.app.e_commerce.Dto.OrdersDto;
import com.app.e_commerce.entity.ECommerceUser;
import com.app.e_commerce.entity.Order;
import com.app.e_commerce.entity.Product;
import com.app.e_commerce.exception.CustomException.CommonException;
import com.app.e_commerce.repository.EcommerceUserRepo;
import com.app.e_commerce.repository.OrderRepo;
import com.app.e_commerce.util.JwtUtil;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
@AllArgsConstructor
public class OrderService {

    private EcommerceUserRepo userRepo;
    private OrderRepo orderRepo;
    private JwtUtil jwtUtil;


//    place order
    public DoneResponce placeOrder(Long userId) {
       ECommerceUser user = userRepo.findById(userId).get();

       if(user.getCart().isEmpty()){
           throw new CommonException("the cart is empty, add product to place the order");
       }

       else {
           Order order = new Order();
           order.setBuyer(user);
           order.setDate(LocalDateTime.now());
           order.setProduct(new ArrayList<>(user.getCart()));
           order.setBuyerId(userId);
           order.setStatus("order-placed");
           orderRepo.save(order);

           user.getCart().clear();
           userRepo.save(user);

           return new DoneResponce("Order placed successfully", true);
       }
    }


    //get order list
    public JwtTokenDto getUserOrder(HttpServletRequest request){

        String pureToken = "";

        for(Cookie cookie : request.getCookies()){
            if("jwt".equals(cookie.getName())){
                pureToken = cookie.getValue();
            }
        }

        String userEmailId = jwtUtil.decodeToken(pureToken).getSubject();
        Optional<ECommerceUser> user = userRepo.findByEmail(userEmailId);

        if(user.get().getOrders().isEmpty()){
            throw new CommonException("you haven't placed any orders");
        }
        else{

            List<Order> orders = new ArrayList<>(user.get().getOrders().stream().filter((order -> !order.getProduct().isEmpty())).toList());
            orders.sort(new DescendingOrderComparator());

            return new JwtTokenDto("token","get ordered list",Optional.of(orders));
        }

    }

    //remove product from orders

    @Transactional
    public DoneResponce removeProduct(Long userId, Long orderId, Long productId) {

        // 1. Fetch user
        ECommerceUser user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // 2. Fetch order belonging to that user
        Order order = orderRepo.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        if (!order.getBuyer().getId().equals(userId)) {
            throw new RuntimeException("Order does not belong to this user");
        }

        // 3. Remove only the first occurrence of the product
        List<Product> products = new ArrayList<>(order.getProduct());

        boolean removed = false;
        for (Iterator<Product> iterator = products.iterator(); iterator.hasNext(); ) {
            Product product = iterator.next();
            if (product.getId().equals(productId)) {
                iterator.remove(); // remove first matching product
                removed = true;
                break; // stop after removing just one
            }
        }

        order.setProduct(products);

        if (!removed) {
            throw new RuntimeException("Product not found in order");
        }


        if (order.getProduct().isEmpty()) {
            orderRepo.delete(order);
        } else {
            orderRepo.save(order);
        }


        return new DoneResponce("One product removed from the order list", true);
    }


    public List<OrdersDto> listOfOrders(int page,int size){

        Pageable pageable = PageRequest.of(page - 1, size, Sort.by("date").descending());

        Page<Order> pageList = orderRepo.findAll(pageable);
        List<Order> orderList = pageList.getContent();

        Iterator<Order> iteratorOrder = orderList.iterator();
        List<OrdersDto> orderDto = new ArrayList<>();
        while (iteratorOrder.hasNext()){
            Order order = iteratorOrder.next();
            ECommerceUser user = userRepo.findById(order.getBuyerId()).get();
            OrdersDto newOrder = new OrdersDto(order.getId(),order.getDate(),user.getName(),user.getPersonalDetail(),order.getProduct(),order.getStatus());
            orderDto.add(newOrder);
        }

        return orderDto;
    }

    public DoneResponce updateOrderStatus(Long orderId,String status){
        Order order = orderRepo.findById(orderId).get();
        order.setStatus(status);
        orderRepo.save(order);

        return new DoneResponce("order status changed",true);
    }
}
      class  DescendingOrderComparator implements Comparator<Order> {
                  @Override
        public int compare(Order o1, Order o2) {
            return Integer.compare(o2.getId().intValue(),o1.getId().intValue()); //put the first argument in the second place and second argument in the first
        }
    }