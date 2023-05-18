package com.example.demo.ServiceImplementation;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.example.demo.Models.Signup;

import com.example.demo.Repository.RepositoryClass;
import com.example.demo.SecurityConfig.UserInfoUserDetails;

@Component
public class UserInfoUserDetailsService implements UserDetailsService {
	@Autowired
	private RepositoryClass repo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
  		Optional<Signup> signup=repo.findUserByEmail(username);
  		System.out.println("signup"+signup);
  		return signup.map(UserInfoUserDetails::new)
  		      .orElseThrow(()-> new UsernameNotFoundException("user details not found"));
		 
	}

}
