package com.greenstitch.controllers;

import java.security.Principal;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.greenstitch.config.SecurityConstants;
import com.greenstitch.dto.JwtResponseDto;

import jakarta.servlet.http.HttpServletResponse;

@RestController
public class LogInController {


	@GetMapping("/logIn")
	public ResponseEntity<JwtResponseDto> getLoggedInUserDetails(Principal principal, HttpServletResponse response){
		
		JwtResponseDto user = new JwtResponseDto();
		user.setToken(response.getHeader(SecurityConstants.JWT_HEADER));
		user.setUsername(principal.getName());
		
		return new ResponseEntity<>(user, HttpStatus.ACCEPTED);
		
	}
}
