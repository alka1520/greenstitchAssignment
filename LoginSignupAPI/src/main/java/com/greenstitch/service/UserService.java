package com.greenstitch.service;

import com.greenstitch.exception.UserException;
import com.greenstitch.model.Consumer;

public interface UserService {
	
	public String registerUser(Consumer user) throws UserException;
	
}
