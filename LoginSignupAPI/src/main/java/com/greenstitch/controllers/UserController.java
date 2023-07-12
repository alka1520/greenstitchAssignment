package com.greenstitch.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.greenstitch.model.Consumer;
import com.greenstitch.repo.UserRepo;
import com.greenstitch.service.UserService;

import jakarta.validation.Valid;

@RestController
public class UserController {

	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/signUp")
	public ResponseEntity<String> signUp(@Valid @RequestBody Consumer user){
		
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		String message = userService.registerUser(user);
		return new ResponseEntity<>(message,HttpStatus.ACCEPTED);
		
	}
	
//	@GetMapping("/consumer")
//	public ResponseEntity<List<Consumer>> getAllConsumerHandler(){
//		
//		List<Consumer> consumer= userRepo.findAll();
//		return new ResponseEntity<>(consumer,HttpStatus.ACCEPTED);
//		
//	}
}
