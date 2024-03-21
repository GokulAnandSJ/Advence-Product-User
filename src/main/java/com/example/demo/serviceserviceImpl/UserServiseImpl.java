package com.example.demo.serviceserviceImpl;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import com.example.demo.utility.ResponseStructure;

@Service
public class UserServiseImpl implements UserService {

	private UserRepository userRepository;
	
	private ResponseStructure<User> responseStructure;
	
	public UserServiseImpl(UserRepository userRepository, ResponseStructure<User> responseStructure) {
		super();
		this.userRepository = userRepository;
		this.responseStructure = responseStructure;
	}

	@Override
	public ResponseEntity<ResponseStructure<User>> addUser(User user) {
		return ResponseEntity.ok(responseStructure.setStatusCode(HttpStatus.OK.value()).setMessage("User Dat saved Sucessfully").setData(userRepository.save(user))) ;
	
	
	
	
	}
}
