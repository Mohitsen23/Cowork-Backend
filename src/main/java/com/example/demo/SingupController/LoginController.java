package com.example.demo.SingupController;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.DTOClasses.LoginDTO;
import com.example.demo.DTOClasses.Update;
import com.example.demo.Models.Signup;
import com.example.demo.SecurityConfig.RepositoryClass;
import com.example.demo.ServiceImplementation.ServiceImplementation;
import com.example.demo.Services.Service;

@RestController
@CrossOrigin
public class LoginController {
	@Autowired
	PasswordEncoder encoder;
@Autowired
private AuthenticationManager authenticationManager;
	@Autowired 
	private RepositoryClass repo;
    @Autowired
	private ServiceImplementation service;
    
    @Autowired
    private Service serv;
    String token=null;
    @GetMapping("/getUser/{email}")
    @CrossOrigin(origins = "http://localhost:4200")
   public Signup getUser(@PathVariable("email") String email ) {
    	Signup userdata=serv.getUser(email); 
    	System.out.println("userdata"+userdata);
    	if(userdata!=null) {
    		return userdata;
    	}
    	return null;
   }
    
    
    @PutMapping("/updatepassword")
    @CrossOrigin(origins = "http://localhost:4200")
    public String updatePassword(@RequestBody Update data) {
    	System.out.println("data input from user"+data);
    	Signup userdata=serv.getUser(data.getEmail()); 
    	System.out.println("userdata"+userdata);
    	if(userdata!=null) {
    	
    		userdata.setPassword(encoder.encode(data.getPassword()));
    		repo.save(userdata);
    		return "password Updated";
    	}
    	return null;
    }
    
    
    
    
    
    
    
    @PostMapping("/loginUser")
    @CrossOrigin(origins = "http://localhost:4200")
   public String login(@RequestBody LoginDTO data) {
    	System.out.println("loginDto data"+ data.getUsername());
    	Signup email=serv.getLogin(data.getUsername()).get();
	    Authentication authenticated =authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(data.getUsername(), data.getPassword())); 

	     if(email!=null) {
          if(authenticated.isAuthenticated()) {
	        
	    	 token= service.getTOken(email.getEmail());
	    	System.out.println("token"+token);
	    	 return token;
	    	 }
	     }
	     
		return null;
	   
   }
   
    
  
   
   @PostMapping("/data")
   public Signup Signup(@RequestBody LoginDTO login) {
	   Signup sign=serv.getLogin(login.getUsername()).get();
	   if(sign!=null) {
		   return sign;
	   }
	   else {
		   return null;
	   }
   }
   
 
   
   
    }
