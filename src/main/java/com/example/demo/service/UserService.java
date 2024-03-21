package com.example.demo.service;

import org.springframework.http.ResponseEntity;

import com.example.demo.entity.User;
import com.example.demo.utility.ResponseStructure;

public interface UserService {

	public abstract ResponseEntity<ResponseStructure<User>> addUser(User user);
}
