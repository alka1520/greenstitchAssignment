package com.greenstitch.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greenstitch.exception.UserException;
import com.greenstitch.model.Consumer;
import com.greenstitch.repo.UserRepo;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepo userRepo;
	
	@Override
	public String registerUser(Consumer user) throws UserException {
		
		 userRepo.save(user);
		 return "Registered Successfully";

	}

}
