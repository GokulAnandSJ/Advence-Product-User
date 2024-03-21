package com.example.demo;

import java.util.Optional;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.entity.Product;

@SpringBootApplication
public class ProductApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductApplication.class, args);

//		Product product = new Product();
//		Optional.of(null).map(g->{System.out.println("Inside Map");
//		return g ;}).orElseThrow(()->new RuntimeException());
		
	}

}
