package com.apis.blog.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.apis.blog.entities.User;
import com.apis.blog.exceptions.ResourceNotFoundException;
import com.apis.blog.repositories.UserRepo;

@Service
public class CustomUserDetailService implements UserDetailsService{

	@Autowired
	private UserRepo userepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//loading user from database by username
		User user = userepo.findByEmail(username).orElseThrow(()-> new ResourceNotFoundException("User","email "+username,0));
		
		return user;
	}

}
