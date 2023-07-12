package com.greenstitch.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.greenstitch.model.Consumer;
import com.greenstitch.repo.UserRepo;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	private UserRepo userRepo;
	
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		Optional<Consumer> opt = userRepo.findByEmail(email);
		System.out.println(opt.get());
		if(opt.isPresent()) {
			Consumer consumer = opt.get();
			List<GrantedAuthority> authorities= new ArrayList<>();
			return new User(consumer.getEmail(), consumer.getPassword(), authorities);		
		} 
		
		else throw new BadCredentialsException("User Details not found with this username: "+email);
		
	}

}
