package com.app.e_commerce;

import com.app.e_commerce.Dto.OrdersDto;
import com.app.e_commerce.entity.Order;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@SpringBootApplication
@OpenAPIDefinition(info = @Info(
		title = "E-commerce-API",
		description = "In this spring-boot application i have implemented, Customer account and Admin panel using sring-security with JWT." +
				"Used mySQL for storing the user data, product, orders" +
				"I have implemented GET, POST, PUT and DELETE HTTP methods",
		version = "1.0.0",
		contact = @Contact(
				name = "Tamilkumaran",
				email = "Tamilkumaran021@gmail.com",
				url = ""
		)
))
public class ECommerceApplication {

	@Bean
	public PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}

	public static void main(String[] args) {
		SpringApplication.run(ECommerceApplication.class, args);
	}

}
